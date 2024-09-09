package cameralib

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.view.KeyEvent
import android.view.Window
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import cameralib.extensions.toast
import cameralib.helpers.Config


abstract class BaseCameraFragment : Fragment() {

    val window: Window?
        get() = activity?.window;

    val applicationContext: Context
        get() = requireContext().applicationContext;

    val isDestroyed: Boolean
        get() = isDetached;

    val config: Config by lazy { Config.newInstance(requireContext()) };

    fun setResult(code: Int, data: Intent?) {
        activity?.setResult(code, data)
    }

    fun finish() {
        activity?.finish();
    }

    fun runOnUiThread(action: Runnable) {
        activity?.runOnUiThread(action)
    }

    fun hasPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED
    }

    private var actionOnPermission: ((granted: Boolean) -> Unit)? = null
    var isAskingPermissions = false

    private var permissionLauncher: ActivityResultLauncher<String> = registerForActivityResult(RequestPermission()) {
        actionOnPermission?.invoke(it)
    }

    fun handlePermission(permission: String, callback: (granted: Boolean) -> Unit) {
        actionOnPermission = null
        if (hasPermission(permission)) {
            callback(true)
        } else {
            isAskingPermissions = true
            actionOnPermission = callback
            permissionLauncher.launch(permission)
        }
    }

    open fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {

        return false;
    }

    open fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        return false;
    }


    fun toast(stringId: Int) {
        context?.toast(stringId)
    }

    open fun onBackPressed(): Boolean {
        return false;
    }
}
