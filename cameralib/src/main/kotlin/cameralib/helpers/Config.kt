package cameralib.helpers

import android.content.Context
import androidx.camera.core.CameraSelector
import cameralib.models.CaptureMode
import cameralib.models.TimerMode

class Config(context: Context) : BaseConfig(context) {
    companion object {
        const val FILE_PROVIDER_AUTHORITY = "Camlib.FILE_PROVIDER_AUTHORITY";

        fun newInstance(context: Context) = Config(context)
    }

    private val defaultFileProviderAuthority: String = "${context.packageName}.fileprovider";
    var fileProviderAuthority: String = prefs.getString(FILE_PROVIDER_AUTHORITY, defaultFileProviderAuthority) ?: defaultFileProviderAuthority;

    var isSoundEnabled: Boolean
        get() = prefs.getBoolean(SOUND, false)
        set(enabled) = prefs.edit().putBoolean(SOUND, enabled).apply()

    var volumeButtonsAsShutter: Boolean
        get() = prefs.getBoolean(VOLUME_BUTTONS_AS_SHUTTER, false)
        set(volumeButtonsAsShutter) = prefs.edit().putBoolean(VOLUME_BUTTONS_AS_SHUTTER, volumeButtonsAsShutter).apply()

    var flipPhotos: Boolean
        get() = prefs.getBoolean(FLIP_PHOTOS, true)
        set(flipPhotos) = prefs.edit().putBoolean(FLIP_PHOTOS, flipPhotos).apply()

    var lastUsedCameraLens: Int
        get() = prefs.getInt(LAST_USED_CAMERA_LENS, CameraSelector.LENS_FACING_BACK)
        set(lens) = prefs.edit().putInt(LAST_USED_CAMERA_LENS, lens).apply()

    var initPhotoMode: Boolean
        get() = prefs.getBoolean(INIT_PHOTO_MODE, true)
        set(initPhotoMode) = prefs.edit().putBoolean(INIT_PHOTO_MODE, initPhotoMode).apply()

    var flashlightState: Int
        get() = prefs.getInt(FLASHLIGHT_STATE, FLASH_OFF)
        set(state) = prefs.edit().putInt(FLASHLIGHT_STATE, state).apply()

    var backPhotoResIndex: Int
        get() = prefs.getInt(BACK_PHOTO_RESOLUTION_INDEX, 0)
        set(backPhotoResIndex) = prefs.edit().putInt(BACK_PHOTO_RESOLUTION_INDEX, backPhotoResIndex).apply()

    var backVideoResIndex: Int
        get() = prefs.getInt(BACK_VIDEO_RESOLUTION_INDEX, 0)
        set(backVideoResIndex) = prefs.edit().putInt(BACK_VIDEO_RESOLUTION_INDEX, backVideoResIndex).apply()

    var frontPhotoResIndex: Int
        get() = prefs.getInt(FRONT_PHOTO_RESOLUTION_INDEX, 0)
        set(frontPhotoResIndex) = prefs.edit().putInt(FRONT_PHOTO_RESOLUTION_INDEX, frontPhotoResIndex).apply()

    var frontVideoResIndex: Int
        get() = prefs.getInt(FRONT_VIDEO_RESOLUTION_INDEX, 0)
        set(frontVideoResIndex) = prefs.edit().putInt(FRONT_VIDEO_RESOLUTION_INDEX, frontVideoResIndex).apply()

    var savePhotoMetadata: Boolean
        get() = prefs.getBoolean(SAVE_PHOTO_METADATA, true)
        set(savePhotoMetadata) = prefs.edit().putBoolean(SAVE_PHOTO_METADATA, savePhotoMetadata).apply()

    var photoQuality: Int
        get() = prefs.getInt(PHOTO_QUALITY, 80)
        set(photoQuality) = prefs.edit().putInt(PHOTO_QUALITY, photoQuality).apply()

    var captureMode: CaptureMode
        get() = CaptureMode.values()[prefs.getInt(CAPTURE_MODE, CaptureMode.MINIMIZE_LATENCY.ordinal)]
        set(captureMode) = prefs.edit().putInt(CAPTURE_MODE, captureMode.ordinal).apply()

    var timerMode: TimerMode
        get() = TimerMode.values().getOrNull(prefs.getInt(TIMER_MODE, TimerMode.OFF.ordinal)) ?: TimerMode.OFF
        set(timerMode) = prefs.edit().putInt(TIMER_MODE, timerMode.ordinal).apply()

}
