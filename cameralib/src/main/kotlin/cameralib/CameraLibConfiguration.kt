package cameralib

import android.content.Context
import android.content.Intent
import java.io.File

class CameraLibConfiguration(val outputFolder: File?) {

    companion object {
        const val EXTRA_OUTPUT_FOLDER = "Camlib.EXTRA_OUTPUT_FOLDER";

        fun fromIntent(intent: Intent): CameraLibConfiguration {
            val outputFolder = if (intent.hasExtra(EXTRA_OUTPUT_FOLDER)) File(intent.getStringExtra(EXTRA_OUTPUT_FOLDER)!!) else null
            return CameraLibConfiguration(outputFolder = outputFolder)
        }
    }

    private fun writeDataToIntent(bundle: Intent) {
        if (outputFolder != null) {
            bundle.putExtra(EXTRA_OUTPUT_FOLDER, outputFolder.absolutePath);
        }
    }

    fun toIntent(context: Context): Intent {
        return Intent(context, CameraActivity::class.java).apply {
            this@CameraLibConfiguration.writeDataToIntent(this)
        }
    }

}
