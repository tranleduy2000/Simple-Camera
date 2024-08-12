package cameralib

import android.content.Context
import android.content.Intent
import java.io.File

class CameraLibConfiguration(val outputFolder: File?, val initCapturePhotoMode: Boolean = true) {

    companion object {
        const val EXTRA_OUTPUT_FOLDER = "Camlib.EXTRA_OUTPUT_FOLDER";
        const val EXTRA_INIT_CAPTURE_PHOTO_MODE = "Camlib.EXTRA_INIT_CAPTURE_PHOTO_MODE";

        fun fromIntent(intent: Intent): CameraLibConfiguration {
            val outputFolder = if (intent.hasExtra(EXTRA_OUTPUT_FOLDER)) File(intent.getStringExtra(EXTRA_OUTPUT_FOLDER)!!) else null
            return CameraLibConfiguration(
                outputFolder = outputFolder,
                initCapturePhotoMode = intent.getBooleanExtra(EXTRA_INIT_CAPTURE_PHOTO_MODE, false)
            )
        }
    }

    private fun writeDataToIntent(bundle: Intent) {
        if (outputFolder != null) {
            bundle.putExtra(EXTRA_OUTPUT_FOLDER, outputFolder.absolutePath);
        }
        bundle.putExtra(EXTRA_INIT_CAPTURE_PHOTO_MODE, initCapturePhotoMode);
    }

    fun toIntent(context: Context): Intent {
        return Intent(context, CameraActivity::class.java).apply {
            this@CameraLibConfiguration.writeDataToIntent(this)
        }
    }

}
