package cameralib.helpers

import android.content.Context
import android.text.format.DateFormat
import cameralib.extensions.getColorAttr
import cameralib.extensions.getSharedPrefs
import java.text.SimpleDateFormat

open class BaseConfig(val context: Context) {
    protected val prefs = context.getSharedPrefs()

    companion object {
        fun newInstance(context: Context) = BaseConfig(context)
    }

    val textColor: Int
        get() =  context.getColorAttr(android.R.attr.textColorPrimary)

    val backgroundColor: Int
        get() =  context.getColorAttr(com.google.android.material.R.attr.colorSurface)

    val primaryColor: Int
        get() =  context.getColorAttr(com.google.android.material.R.attr.colorPrimary)

    val accentColor: Int
        get() =  context.getColorAttr(com.google.android.material.R.attr.colorPrimary)

    var isUsingSystemTheme: Boolean
        get() = prefs.getBoolean(IS_USING_SYSTEM_THEME, isSPlus())
        set(isUsingSystemTheme) = prefs.edit().putBoolean(IS_USING_SYSTEM_THEME, isUsingSystemTheme).apply()

    var use24HourFormat: Boolean
        get() = prefs.getBoolean(USE_24_HOUR_FORMAT, DateFormat.is24HourFormat(context))
        set(use24HourFormat) = prefs.edit().putBoolean(USE_24_HOUR_FORMAT, use24HourFormat).apply()

    var dateFormat: String
        get() = prefs.getString(DATE_FORMAT, getDefaultDateFormat())!!
        set(dateFormat) = prefs.edit().putString(DATE_FORMAT, dateFormat).apply()

    private fun getDefaultDateFormat(): String {
        val format = DateFormat.getDateFormat(context)
        val pattern = (format as SimpleDateFormat).toLocalizedPattern()
        return when (pattern.lowercase().replace(" ", "")) {
            "d.M.y" -> DATE_FORMAT_ONE
            "dd/mm/y" -> DATE_FORMAT_TWO
            "mm/dd/y" -> DATE_FORMAT_THREE
            "y-mm-dd" -> DATE_FORMAT_FOUR
            "dmmmmy" -> DATE_FORMAT_FIVE
            "mmmmdy" -> DATE_FORMAT_SIX
            "mm-dd-y" -> DATE_FORMAT_SEVEN
            "dd-mm-y" -> DATE_FORMAT_EIGHT
            else -> DATE_FORMAT_ONE
        }
    }

}
