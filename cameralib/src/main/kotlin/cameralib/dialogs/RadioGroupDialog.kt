package cameralib.dialogs

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import cameralib.R
import cameralib.databinding.CamlibDialogRadioGroupBinding
import cameralib.extensions.onGlobalLayout
import cameralib.models.RadioItem
import getAlertDialogBuilder
import setupDialogStuff

class RadioGroupDialog(
    val activity: Activity, val items: ArrayList<RadioItem>, val checkedItemId: Int = -1, val titleId: Int = 0,
    showOKButton: Boolean = false, val cancelCallback: (() -> Unit)? = null, val callback: (newValue: Any) -> Unit
) {
    private var dialog: AlertDialog? = null
    private var wasInit = false
    private var selectedItemId = -1

    init {
        val view = CamlibDialogRadioGroupBinding.inflate(activity.layoutInflater, null, false)
        view.dialogRadioGroup.apply {
            for (i in 0 until items.size) {
                val radioButton = (activity.layoutInflater.inflate(R.layout.camlib_radio_button, null) as RadioButton).apply {
                    text = items[i].title
                    isChecked = items[i].id == checkedItemId
                    id = i
                    setOnClickListener { itemSelected(i) }
                }

                if (items[i].id == checkedItemId) {
                    selectedItemId = i
                }

                addView(radioButton, RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
            }
        }

        val builder = activity.getAlertDialogBuilder()
            .setOnCancelListener { cancelCallback?.invoke() }

        if (selectedItemId != -1 && showOKButton) {
            builder.setPositiveButton(R.string.camlib_ok) { dialog, which -> itemSelected(selectedItemId) }
        }

        builder.apply {
            activity.setupDialogStuff(view.root, this, titleId) { alertDialog ->
                dialog = alertDialog
            }
        }

        if (selectedItemId != -1) {
            view.dialogRadioHolder.apply {
                onGlobalLayout {
                    scrollY = view.dialogRadioGroup.findViewById<View>(selectedItemId).bottom - height
                }
            }
        }

        wasInit = true
    }

    private fun itemSelected(checkedId: Int) {
        if (wasInit) {
            callback(items[checkedId].value)
            dialog?.dismiss()
        }
    }
}
