package cameralib.models

import androidx.annotation.StringRes
import cameralib.R

enum class CaptureMode(@StringRes val stringResId: Int) {
    MINIMIZE_LATENCY(R.string.camlib_minimize_latency),
    MAXIMIZE_QUALITY(R.string.camlib_maximize_quality)
}
