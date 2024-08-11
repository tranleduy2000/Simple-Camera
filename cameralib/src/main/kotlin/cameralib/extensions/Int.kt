package cameralib.extensions

import android.graphics.Color
import android.media.ExifInterface
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import cameralib.R
import cameralib.helpers.*
import java.util.Locale

fun Int.toCameraXFlashMode(): Int {
    return when (this) {
        FLASH_ON -> ImageCapture.FLASH_MODE_ON
        FLASH_OFF -> ImageCapture.FLASH_MODE_OFF
        FLASH_AUTO -> ImageCapture.FLASH_MODE_AUTO
        FLASH_ALWAYS_ON -> ImageCapture.FLASH_MODE_OFF
        else -> throw IllegalArgumentException("Unknown mode: $this")
    }
}

fun Int.toAppFlashMode(): Int {
    return when (this) {
        ImageCapture.FLASH_MODE_ON -> FLASH_ON
        ImageCapture.FLASH_MODE_OFF -> FLASH_OFF
        ImageCapture.FLASH_MODE_AUTO -> FLASH_AUTO
        else -> throw IllegalArgumentException("Unknown mode: $this")
    }
}

fun Int.toFlashModeId(): Int {
    return when (this) {
        FLASH_ON -> R.id.flash_on
        FLASH_OFF -> R.id.flash_off
        FLASH_AUTO -> R.id.flash_auto
        FLASH_ALWAYS_ON -> R.id.flash_always_on
        else -> throw IllegalArgumentException("Unknown mode: $this")
    }
}

fun Int.toCameraSelector(): CameraSelector {
    return if (this == CameraSelector.LENS_FACING_FRONT) {
        CameraSelector.DEFAULT_FRONT_CAMERA
    } else {
        CameraSelector.DEFAULT_BACK_CAMERA
    }
}


fun Int.orientationFromDegrees() = when (this) {
    270 -> ExifInterface.ORIENTATION_ROTATE_270
    180 -> ExifInterface.ORIENTATION_ROTATE_180
    90 -> ExifInterface.ORIENTATION_ROTATE_90
    else -> ExifInterface.ORIENTATION_NORMAL
}.toString()


fun Int.degreesFromOrientation() = when (this) {
    ExifInterface.ORIENTATION_ROTATE_270 -> 270
    ExifInterface.ORIENTATION_ROTATE_180 -> 180
    ExifInterface.ORIENTATION_ROTATE_90 -> 90
    else -> 0
}


fun Int.getFormattedDuration(forceShowHours: Boolean = false): String {
    val sb = StringBuilder(8)
    val hours = this / 3600
    val minutes = this % 3600 / 60
    val seconds = this % 60

    if (this >= 3600) {
        sb.append(String.format(Locale.getDefault(), "%02d", hours)).append(":")
    } else if (forceShowHours) {
        sb.append("0:")
    }

    sb.append(String.format(Locale.getDefault(), "%02d", minutes))
    sb.append(":").append(String.format(Locale.getDefault(), "%02d", seconds))
    return sb.toString()
}


fun Int.addBitIf(add: Boolean, bit: Int) =
    if (add) {
        addBit(bit)
    } else {
        removeBit(bit)
    }

fun Int.removeBit(bit: Int) = addBit(bit) - bit

fun Int.addBit(bit: Int) = this or bit

fun Int.flipBit(bit: Int) = if (this and bit == 0) addBit(bit) else removeBit(bit)

fun Int.getContrastColor(): Int {
    val y = (299 * Color.red(this) + 587 * Color.green(this) + 114 * Color.blue(this)) / 1000
    return if (y >= 149 && this != Color.BLACK) DARK_GREY else Color.WHITE
}


fun Int.adjustAlpha(factor: Float): Int {
    val alpha = Math.round(Color.alpha(this) * factor)
    val red = Color.red(this)
    val green = Color.green(this)
    val blue = Color.blue(this)
    return Color.argb(alpha, red, green, blue)
}
