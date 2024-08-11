package cameralib.extensions

import android.content.Context
import android.content.Intent
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Environment
import androidx.documentfile.provider.DocumentFile
import cameralib.R
import cameralib.helpers.isRPlus
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

fun Context.getInternalStoragePath() =
    if (File("/storage/emulated/0").exists()) "/storage/emulated/0" else Environment.getExternalStorageDirectory().absolutePath.trimEnd('/')

fun Context.isSDCardSetAsDefaultStorage() = sdCardPath.isNotEmpty() && Environment.getExternalStorageDirectory().absolutePath.equals(sdCardPath, true)

// http://stackoverflow.com/a/40582634/1967672
fun Context.getSDCardPath(): String {
//    val directories = getStorageDirectories().filter {
//        !it.equals(getInternalStoragePath()) && !it.equals(
//            "/storage/emulated/0",
//            true
//        ) && (baseConfig.OTGPartition.isEmpty() || !it.endsWith(baseConfig.OTGPartition))
//    }
//
//    val fullSDpattern = Pattern.compile(SD_OTG_PATTERN)
//    var sdCardPath = directories.firstOrNull { fullSDpattern.matcher(it).matches() }
//        ?: directories.firstOrNull { !physicalPaths.contains(it.toLowerCase()) } ?: ""
//
//    // on some devices no method retrieved any SD card path, so test if its not sdcard1 by any chance. It happened on an Android 5.1
//    if (sdCardPath.trimEnd('/').isEmpty()) {
//        val file = File("/storage/sdcard1")
//        if (file.exists()) {
//            return file.absolutePath
//        }
//
//        sdCardPath = directories.firstOrNull() ?: ""
//    }
//
//    if (sdCardPath.isEmpty()) {
//        val SDpattern = Pattern.compile(SD_OTG_SHORT)
//        try {
//            File("/storage").listFiles()?.forEach {
//                if (SDpattern.matcher(it.name).matches()) {
//                    sdCardPath = "/storage/${it.name}"
//                }
//            }
//        } catch (e: Exception) {
//        }
//    }
//
//    val finalPath = sdCardPath.trimEnd('/')
//    baseConfig.sdCardPath = finalPath
//    return finalPath
    return ""
}

private const val ANDROID_DATA_DIR = "/Android/data/"
private const val ANDROID_OBB_DIR = "/Android/obb/"
val DIRS_ACCESSIBLE_ONLY_WITH_SAF = listOf(ANDROID_DATA_DIR, ANDROID_OBB_DIR)

fun Context.getSAFOnlyDirs(): List<String> {
    return DIRS_ACCESSIBLE_ONLY_WITH_SAF.map { "$internalStoragePath$it" } +
        DIRS_ACCESSIBLE_ONLY_WITH_SAF.map { "$sdCardPath$it" }
}

fun Context.isSAFOnlyRoot(path: String): Boolean {
    return getSAFOnlyDirs().any { "${path.trimEnd('/')}/".startsWith(it) }
}

fun Context.isRestrictedSAFOnlyRoot(path: String): Boolean {
    return isRPlus() && isSAFOnlyRoot(path)
}

//
//fun Context.isAccessibleWithSAFSdk30(path: String): Boolean {
//    if (path.startsWith(recycleBinPath) || isExternalStorageManager()) {
//        return false
//    }
//
//    val level = getFirstParentLevel(path)
//    val firstParentDir = path.getFirstParentDirName(this, level)
//    val firstParentPath = path.getFirstParentPath(this, level)
//
//    val isValidName = firstParentDir != null
//    val isDirectory = File(firstParentPath).isDirectory
//    val isAnAccessibleDirectory = DIRS_INACCESSIBLE_WITH_SAF_SDK_30.all { !firstParentDir.equals(it, true) }
//    return isRPlus() && isValidName && isDirectory && isAnAccessibleDirectory
//}

fun Context.getFileOutputStreamSync(path: String, mimeType: String, parentDocumentFile: DocumentFile? = null): OutputStream? {
    val targetFile = File(path)

    return when {
//        isRestrictedSAFOnlyRoot(path) -> {
//            val uri = getAndroidSAFUri(path)
//            if (!getDoesFilePathExist(path)) {
//                createAndroidSAFFile(path)
//            }
//            applicationContext.contentResolver.openOutputStream(uri, "wt")
//        }
//        needsStupidWritePermissions(path) -> {
//            var documentFile = parentDocumentFile
//            if (documentFile == null) {
//                if (getDoesFilePathExist(targetFile.parentFile.absolutePath)) {
//                    documentFile = getDocumentFile(targetFile.parent)
//                } else {
//                    documentFile = getDocumentFile(targetFile.parentFile.parent)
//                    documentFile = documentFile!!.createDirectory(targetFile.parentFile.name) ?: getDocumentFile(targetFile.parentFile.absolutePath)
//                }
//            }
//
//            if (documentFile == null) {
//                val casualOutputStream = createCasualFileOutputStream(targetFile)
//                return if (casualOutputStream == null) {
//                    showFileCreateError(targetFile.parent)
//                    null
//                } else {
//                    casualOutputStream
//                }
//            }
//
//            try {
//                val uri = if (getDoesFilePathExist(path)) {
//                    createDocumentUriFromRootTree(path)
//                } else {
//                    documentFile.createFile(mimeType, path.getFilenameFromPath())!!.uri
//                }
//                applicationContext.contentResolver.openOutputStream(uri, "wt")
//            } catch (e: Exception) {
//                showErrorToast(e)
//                null
//            }
//        }
//        isAccessibleWithSAFSdk30(path) -> {
//            try {
//                val uri = createDocumentUriUsingFirstParentTreeUri(path)
//                if (!getDoesFilePathExist(path)) {
//                    createSAFFileSdk30(path)
//                }
//                applicationContext.contentResolver.openOutputStream(uri, "wt")
//            } catch (e: Exception) {
//                null
//            } ?: createCasualFileOutputStream(targetFile)
//        }
        else -> return createCasualFileOutputStream(targetFile)
    }
}

fun Context.showFileCreateError(path: String) {
    val error = String.format(getString(R.string.could_not_create_file), path)
    baseConfig.sdTreeUri = ""
    showErrorToast(error)
}

private fun Context.createCasualFileOutputStream(targetFile: File): OutputStream? {
    if (targetFile.parentFile?.exists() == false) {
        targetFile.parentFile?.mkdirs()
    }

    return try {
        FileOutputStream(targetFile)
    } catch (e: Exception) {
        showErrorToast(e)
        null
    }
}


fun Context.hasProperStoredTreeUri(isOTG: Boolean): Boolean {
    val uri = if (isOTG) baseConfig.OTGTreeUri else baseConfig.sdTreeUri
    val hasProperUri = contentResolver.persistedUriPermissions.any { it.uri.toString() == uri }
    if (!hasProperUri) {
        if (isOTG) {
            baseConfig.OTGTreeUri = ""
        } else {
            baseConfig.sdTreeUri = ""
        }
    }
    return hasProperUri
}


// avoid calling this multiple times in row, it can delete whole folder contents
fun Context.rescanPaths(paths: List<String>, callback: (() -> Unit)? = null) {
    if (paths.isEmpty()) {
        callback?.invoke()
        return
    }

    for (path in paths) {
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).apply {
            data = Uri.fromFile(File(path))
            sendBroadcast(this)
        }
    }

    var cnt = paths.size
    MediaScannerConnection.scanFile(applicationContext, paths.toTypedArray(), null) { s, uri ->
        if (--cnt == 0) {
            callback?.invoke()
        }
    }
}
