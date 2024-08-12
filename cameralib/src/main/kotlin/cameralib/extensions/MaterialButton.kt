package cameralib.extensions

import androidx.annotation.DrawableRes
import cameralib.R
import cameralib.views.ShadowDrawable
import com.google.android.material.button.MaterialButton

fun MaterialButton.setShadowIcon(@DrawableRes drawableResId: Int) {
    icon = ShadowDrawable(context, drawableResId, R.style.CamlibTopIconShadow)
}
