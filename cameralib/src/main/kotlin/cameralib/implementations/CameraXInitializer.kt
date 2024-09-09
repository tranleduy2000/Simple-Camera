package cameralib.implementations

import android.net.Uri
import androidx.camera.view.PreviewView
import androidx.fragment.app.FragmentActivity
import cameralib.CameraLibConfiguration
import cameralib.helpers.CameraErrorHandler
import cameralib.helpers.MediaOutputHelper
import cameralib.helpers.MediaSoundHelper

class CameraXInitializer(
    private val activity: FragmentActivity,
    private val cameraLibConfiguration: CameraLibConfiguration = CameraLibConfiguration.fromIntent(activity.intent)
) {

    fun createCameraXPreview(
        previewView: PreviewView,
        listener: CameraXPreviewListener,
        mediaSoundHelper: MediaSoundHelper,
        outputUri: Uri?,
        initInPhotoMode: Boolean,
    ): CameraXPreview {
        val cameraErrorHandler = newCameraErrorHandler()
        val mediaOutputHelper = newMediaOutputHelper(cameraErrorHandler, outputUri)
        return CameraXPreview(
            activity,
            previewView,
            mediaSoundHelper,
            mediaOutputHelper,
            cameraErrorHandler,
            listener,
            initInPhotoMode = initInPhotoMode,
        )
    }

    private fun newMediaOutputHelper(
        cameraErrorHandler: CameraErrorHandler,
        outputUri: Uri?,
    ): MediaOutputHelper {
        return MediaOutputHelper(
            activity,
            cameraErrorHandler,
            outputUri,
            cameraLibConfiguration.outputFolder
        )
    }

    private fun newCameraErrorHandler(): CameraErrorHandler {
        return CameraErrorHandler(activity)
    }
}
