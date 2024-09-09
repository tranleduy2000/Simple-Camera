package cameralib

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.WindowManager
import androidx.activity.SystemBarStyle
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat

class CameraActivity : BaseSimpleActivity() {

    private var cameraFragment: CameraFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableFullyEdgeToEdge()
        setContentView(R.layout.camlib_activity_camera)

        cameraFragment = supportFragmentManager.findFragmentByTag("CameraFragment") as? CameraFragment
            ?: CameraFragment.newInstance(intent.extras)
        supportFragmentManager.beginTransaction()
            .replace(R.id.camlib_camera_fragment, cameraFragment!!)
            .commit()

        onBackPressedDispatcher.addCallback {
            if (cameraFragment?.onBackPressed() == true) {
                return@addCallback
            }
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        ensureTransparentNavigationBar()
    }

    private fun enableFullyEdgeToEdge() {
        enableEdgeToEdge(navigationBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT))
        if (Build.VERSION.SDK_INT >= 29) {
            window.isNavigationBarContrastEnforced = false
        }
    }

    private fun ensureTransparentNavigationBar() {
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window?.navigationBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        window?.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)

        window?.isNavigationBarContrastEnforced = false;
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return cameraFragment?.onKeyDown(keyCode, event) ?: super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        return cameraFragment?.onKeyDown(keyCode, event) ?: super.onKeyUp(keyCode, event)
    }
}
