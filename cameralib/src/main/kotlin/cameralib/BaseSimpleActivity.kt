package cameralib

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.graphics.Color
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.WindowInsetsCompat
import cameralib.extensions.*
import hideKeyboard
import onApplyWindowInsets

abstract class BaseSimpleActivity : AppCompatActivity() {
    var actionOnPermission: ((granted: Boolean) -> Unit)? = null
    var isAskingPermissions = false
    var showTransparentTop = false

    private var mainCoordinatorLayout: CoordinatorLayout? = null
    private var nestedView: View? = null
    private var useTransparentNavigation = false
    private val GENERIC_PERM_HANDLER = 100

    @SuppressLint("NewApi")
    override fun onResume() {
        super.onResume()

        if (showTransparentTop) {
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        actionOnPermission = null
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        handleNavigationAndScrolling()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                hideKeyboard()
                finish()
            }

            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun handleNavigationAndScrolling() {
        if (useTransparentNavigation) {
            if (navigationBarHeight > 0 || isUsingGestureNavigation()) {
                window.decorView.systemUiVisibility = window.decorView.systemUiVisibility.addBit(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
                updateTopBottomInsets(statusBarHeight, navigationBarHeight)
                // Don't touch this. Window Inset API often has a domino effect and things will most likely break.
                onApplyWindowInsets {
                    val insets = it.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime())
                    updateTopBottomInsets(insets.top, insets.bottom)
                }
            } else {
                window.decorView.systemUiVisibility = window.decorView.systemUiVisibility.removeBit(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
                updateTopBottomInsets(0, 0)
            }
        }
    }

    private fun updateTopBottomInsets(top: Int, bottom: Int) {
        nestedView?.run {
            setPadding(paddingLeft, paddingTop, paddingRight, bottom)
        }
        (mainCoordinatorLayout?.layoutParams as? FrameLayout.LayoutParams)?.topMargin = top
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        isAskingPermissions = false
        if (requestCode == GENERIC_PERM_HANDLER && grantResults.isNotEmpty()) {
            actionOnPermission?.invoke(grantResults[0] == 0)
        }
    }

}
