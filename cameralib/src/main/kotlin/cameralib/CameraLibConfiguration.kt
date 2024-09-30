package cameralib

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import java.io.File

class CameraLibConfiguration(
    val outputFolder: File?,
    val initCapturePhotoMode: Boolean = true,
    val showBackButton: Boolean = false
) {

    companion object {
        const val EXTRA_OUTPUT_FOLDER = "Camlib.EXTRA_OUTPUT_FOLDER";
        const val EXTRA_INIT_CAPTURE_PHOTO_MODE = "Camlib.EXTRA_INIT_CAPTURE_PHOTO_MODE";
        const val EXTRA_SHOW_BACK_BUTTON = "Camlib.EXTRA_SHOW_BACK_BUTTON";

        fun fromIntent(intent: Intent): CameraLibConfiguration {
            return fromBundle(intent.extras ?: Bundle())
        }

        fun fromBundle(intent: Bundle): CameraLibConfiguration {
            val outputFolder = if (intent.containsKey(EXTRA_OUTPUT_FOLDER)) File(intent.getString(EXTRA_OUTPUT_FOLDER)!!) else null
            return CameraLibConfiguration(
                outputFolder = outputFolder,
                initCapturePhotoMode = intent.getBoolean(EXTRA_INIT_CAPTURE_PHOTO_MODE, false),
                showBackButton = intent.getBoolean(EXTRA_SHOW_BACK_BUTTON, false)
            )
        }
    }

    fun writeDataToIntent(bundle: Intent) {
        if (outputFolder != null) {
            bundle.putExtra(EXTRA_OUTPUT_FOLDER, outputFolder.absolutePath);
        }
        bundle.putExtra(EXTRA_INIT_CAPTURE_PHOTO_MODE, initCapturePhotoMode);
        bundle.putExtra(EXTRA_SHOW_BACK_BUTTON, showBackButton);
    }

    fun toIntent(context: Context, activityClass: Class<out Activity> = CameraActivity::class.java): Intent {
        return Intent(context, activityClass).apply {
            this@CameraLibConfiguration.writeDataToIntent(this)
        }
    }

}
