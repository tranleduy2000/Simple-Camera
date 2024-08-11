package cameralib.extensions

import java.io.File

fun File.isMediaFile() = absolutePath.isMediaFile()
