package cameralib.helpers

import android.content.ContentValues
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import cameralib.extensions.getOutputMediaFilePath
import cameralib.extensions.getRandomMediaName
import cameralib.models.MediaOutput
import java.io.File

class MediaOutputHelper(
    private val activity: AppCompatActivity,
    private val errorHandler: CameraErrorHandler,
    private val outputUri: Uri?,
    private val outputFolder: File?
) {

    companion object {
        private const val EXTERNAL_VOLUME = "external"
        private const val IMAGE_MIME_TYPE = "image/jpeg"
        private const val VIDEO_MIME_TYPE = "video/mp4"
    }

    fun getImageMediaOutput(): MediaOutput.ImageCaptureOutput {
        return try {
            getFileMediaOutput(isPhoto = true) ?: getMediaStoreOutput(isPhoto = true)
        } catch (e: Exception) {
            errorHandler.showSaveToInternalStorage()
            getMediaStoreOutput(isPhoto = true)
        }
    }

    fun getVideoMediaOutput(): MediaOutput.VideoCaptureOutput {
        return try {
            getFileMediaOutput(isPhoto = false) ?: getMediaStoreOutput(isPhoto = false)
        } catch (e: Exception) {
            errorHandler.showSaveToInternalStorage()
            getMediaStoreOutput(isPhoto = false)
        }
    }

    private fun getFileMediaOutput(isPhoto: Boolean): MediaOutput.FileMediaOutput? {
        val outputFolder = outputFolder ?: return null;
        var mediaOutput: MediaOutput.FileMediaOutput? = null
        val canWrite = outputFolder.canWrite();
        if (canWrite) {
            val path = activity.getOutputMediaFilePath(outputFolder, isPhoto)
            val uri = getUriForFilePath(path)
            if (uri != null) {
                mediaOutput = MediaOutput.FileMediaOutput(File(path), uri)
            }
        }
        return mediaOutput
    }

    private fun getMediaStoreOutput(isPhoto: Boolean): MediaOutput.MediaStoreOutput {
        val contentValues = getContentValues(isPhoto)
        val contentUri = if (isPhoto) {
            MediaStore.Images.Media.getContentUri(EXTERNAL_VOLUME)
        } else {
            MediaStore.Video.Media.getContentUri(EXTERNAL_VOLUME)
        }
        return MediaOutput.MediaStoreOutput(contentValues, contentUri)
    }

    private fun getContentValues(isPhoto: Boolean): ContentValues {
        val mimeType = if (isPhoto) IMAGE_MIME_TYPE else VIDEO_MIME_TYPE
        return ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, getRandomMediaName(isPhoto))
            put(MediaStore.MediaColumns.MIME_TYPE, mimeType)
            if (isQPlus()) {
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM)
            } else {
                put(MediaStore.MediaColumns.DATA, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString())
            }
        }
    }


    private fun getUriForFilePath(path: String): Uri? {
        val targetFile = File(path)
        return Uri.fromFile(targetFile)
    }

}
