package cameralib.extensions

import android.content.Context

// handle system default theme (Material You) specially as the color is taken from the system, not hardcoded by us
fun Context.getProperTextColor() = baseConfig.textColor


fun Context.getProperBackgroundColor() = baseConfig.backgroundColor


fun Context.getProperStatusBarColor() = when {
    else -> getProperBackgroundColor()
}

// get the color of the statusbar with material activity, if the layout is scrolled down a bit
fun Context.getColoredMaterialStatusBarColor(): Int {
    return getProperPrimaryColor()
}
