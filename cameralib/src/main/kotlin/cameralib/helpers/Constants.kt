package cameralib.helpers

import android.os.Build
import android.os.Looper
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.StringRes
import cameralib.R

const val ORIENT_PORTRAIT = 0
const val ORIENT_LANDSCAPE_LEFT = 1
const val ORIENT_LANDSCAPE_RIGHT = 2

// shared preferences
const val SAVE_PHOTOS = "save_photos"
const val SOUND = "sound"
const val VOLUME_BUTTONS_AS_SHUTTER = "volume_buttons_as_shutter"
const val FLIP_PHOTOS = "flip_photos"
const val LAST_USED_CAMERA = "last_used_camera_3"
const val LAST_USED_CAMERA_LENS = "last_used_camera_lens"
const val FLASHLIGHT_STATE = "flashlight_state"
const val INIT_PHOTO_MODE = "init_photo_mode"
const val BACK_PHOTO_RESOLUTION_INDEX = "back_photo_resolution_index_3"
const val BACK_VIDEO_RESOLUTION_INDEX = "back_video_resolution_index_3"
const val FRONT_PHOTO_RESOLUTION_INDEX = "front_photo_resolution_index_3"
const val FRONT_VIDEO_RESOLUTION_INDEX = "front_video_resolution_index_3"
const val SAVE_PHOTO_METADATA = "save_photo_metadata"
const val SAVE_PHOTO_VIDEO_LOCATION = "save_photo_video_location"
const val PHOTO_QUALITY = "photo_quality"
const val CAPTURE_MODE = "capture_mode"
const val TIMER_MODE = "timer_mode"

const val FLASH_OFF = 0
const val FLASH_ON = 1
const val FLASH_AUTO = 2
const val FLASH_ALWAYS_ON = 3

fun compensateDeviceRotation(orientation: Int) = when (orientation) {
    ORIENT_LANDSCAPE_LEFT -> 270
    ORIENT_LANDSCAPE_RIGHT -> 90
    else -> 0
}


// permissions
const val PERMISSION_READ_STORAGE = 1
const val PERMISSION_WRITE_STORAGE = 2
const val PERMISSION_CAMERA = 3
const val PERMISSION_RECORD_AUDIO = 4
const val PERMISSION_READ_CONTACTS = 5
const val PERMISSION_WRITE_CONTACTS = 6
const val PERMISSION_READ_CALENDAR = 7
const val PERMISSION_WRITE_CALENDAR = 8
const val PERMISSION_CALL_PHONE = 9
const val PERMISSION_READ_CALL_LOG = 10
const val PERMISSION_WRITE_CALL_LOG = 11
const val PERMISSION_GET_ACCOUNTS = 12
const val PERMISSION_READ_SMS = 13
const val PERMISSION_SEND_SMS = 14
const val PERMISSION_READ_PHONE_STATE = 15
const val PERMISSION_MEDIA_LOCATION = 16
const val PERMISSION_POST_NOTIFICATIONS = 17
const val PERMISSION_READ_MEDIA_IMAGES = 18
const val PERMISSION_READ_MEDIA_VIDEO = 19
const val PERMISSION_READ_MEDIA_AUDIO = 20
const val PERMISSION_ACCESS_COARSE_LOCATION = 21
const val PERMISSION_ACCESS_FINE_LOCATION = 22
const val PERMISSION_READ_MEDIA_VISUAL_USER_SELECTED = 23


@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q)
fun isQPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.N)
fun isNougatPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.N_MR1)
fun isNougatMR1Plus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O)
fun isOreoPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O_MR1)
fun isOreoMr1Plus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.P)
fun isPiePlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
fun isRPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun isSPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.TIRAMISU)
fun isTiramisuPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun isUpsideDownCakePlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE

const val SHORT_ANIMATION_DURATION = 150L

fun isOnMainThread() = Looper.myLooper() == Looper.getMainLooper()

fun ensureBackgroundThread(callback: () -> Unit) {
    if (isOnMainThread()) {
        Thread {
            callback()
        }.start()
    } else {
        callback()
    }
}

const val PREFS_KEY = "Prefs"
const val APP_RUN_COUNT = "app_run_count"
const val LAST_VERSION = "last_version"
const val SD_TREE_URI = "tree_uri_2"
const val PRIMARY_ANDROID_DATA_TREE_URI = "primary_android_data_tree_uri_2"
const val OTG_ANDROID_DATA_TREE_URI = "otg_android_data_tree__uri_2"
const val SD_ANDROID_DATA_TREE_URI = "sd_android_data_tree_uri_2"
const val PRIMARY_ANDROID_OBB_TREE_URI = "primary_android_obb_tree_uri_2"
const val OTG_ANDROID_OBB_TREE_URI = "otg_android_obb_tree_uri_2"
const val SD_ANDROID_OBB_TREE_URI = "sd_android_obb_tree_uri_2"
const val OTG_TREE_URI = "otg_tree_uri_2"
const val SD_CARD_PATH = "sd_card_path_2"
const val OTG_REAL_PATH = "otg_real_path_2"
const val INTERNAL_STORAGE_PATH = "internal_storage_path"
const val TEXT_COLOR = "text_color"
const val BACKGROUND_COLOR = "background_color"
const val PRIMARY_COLOR = "primary_color_2"
const val ACCENT_COLOR = "accent_color"
const val APP_ICON_COLOR = "app_icon_color"
const val LAST_HANDLED_SHORTCUT_COLOR = "last_handled_shortcut_color"
const val LAST_ICON_COLOR = "last_icon_color"
const val CUSTOM_TEXT_COLOR = "custom_text_color"
const val CUSTOM_BACKGROUND_COLOR = "custom_background_color"
const val CUSTOM_PRIMARY_COLOR = "custom_primary_color"
const val CUSTOM_ACCENT_COLOR = "custom_accent_color"
const val CUSTOM_APP_ICON_COLOR = "custom_app_icon_color"
const val WIDGET_BG_COLOR = "widget_bg_color"
const val WIDGET_TEXT_COLOR = "widget_text_color"
const val PASSWORD_PROTECTION = "password_protection"
const val PASSWORD_HASH = "password_hash"
const val PROTECTION_TYPE = "protection_type"
const val APP_PASSWORD_PROTECTION = "app_password_protection"
const val APP_PASSWORD_HASH = "app_password_hash"
const val APP_PROTECTION_TYPE = "app_protection_type"
const val DELETE_PASSWORD_PROTECTION = "delete_password_protection"
const val DELETE_PASSWORD_HASH = "delete_password_hash"
const val DELETE_PROTECTION_TYPE = "delete_protection_type"
const val PROTECTED_FOLDER_PATH = "protected_folder_path_"
const val PROTECTED_FOLDER_HASH = "protected_folder_hash_"
const val PROTECTED_FOLDER_TYPE = "protected_folder_type_"
const val KEEP_LAST_MODIFIED = "keep_last_modified"
const val USE_ENGLISH = "use_english"
const val WAS_USE_ENGLISH_TOGGLED = "was_use_english_toggled"
const val WAS_SHARED_THEME_EVER_ACTIVATED = "was_shared_theme_ever_activated"
const val IS_USING_SHARED_THEME = "is_using_shared_theme"
const val IS_USING_AUTO_THEME = "is_using_auto_theme"
const val IS_USING_SYSTEM_THEME = "is_using_system_theme"
const val SHOULD_USE_SHARED_THEME = "should_use_shared_theme"
const val WAS_SHARED_THEME_FORCED = "was_shared_theme_forced"
const val WAS_CUSTOM_THEME_SWITCH_DESCRIPTION_SHOWN = "was_custom_theme_switch_description_shown"
const val SHOW_INFO_BUBBLE = "show_info_bubble"
const val LAST_CONFLICT_RESOLUTION = "last_conflict_resolution"
const val LAST_CONFLICT_APPLY_TO_ALL = "last_conflict_apply_to_all"
const val LAST_COPY_PATH = "last_copy_path"
const val HAD_THANK_YOU_INSTALLED = "had_thank_you_installed"
const val SKIP_DELETE_CONFIRMATION = "skip_delete_confirmation"
const val ENABLE_PULL_TO_REFRESH = "enable_pull_to_refresh"
const val SCROLL_HORIZONTALLY = "scroll_horizontally"
const val PREVENT_PHONE_FROM_SLEEPING = "prevent_phone_from_sleeping"
const val LAST_USED_VIEW_PAGER_PAGE = "last_used_view_pager_page"
const val USE_24_HOUR_FORMAT = "use_24_hour_format"
const val SUNDAY_FIRST = "sunday_first"
const val WAS_ALARM_WARNING_SHOWN = "was_alarm_warning_shown"
const val WAS_REMINDER_WARNING_SHOWN = "was_reminder_warning_shown"
const val USE_SAME_SNOOZE = "use_same_snooze"
const val SNOOZE_TIME = "snooze_delay"
const val VIBRATE_ON_BUTTON_PRESS = "vibrate_on_button_press"
const val YOUR_ALARM_SOUNDS = "your_alarm_sounds"
const val SILENT = "silent"
const val OTG_PARTITION = "otg_partition_2"
const val IS_USING_MODIFIED_APP_ICON = "is_using_modified_app_icon"
const val INITIAL_WIDGET_HEIGHT = "initial_widget_height"
const val WIDGET_ID_TO_MEASURE = "widget_id_to_measure"
const val WAS_ORANGE_ICON_CHECKED = "was_orange_icon_checked"
const val WAS_APP_ON_SD_SHOWN = "was_app_on_sd_shown"
const val WAS_BEFORE_ASKING_SHOWN = "was_before_asking_shown"
const val WAS_BEFORE_RATE_SHOWN = "was_before_rate_shown"
const val WAS_INITIAL_UPGRADE_TO_PRO_SHOWN = "was_initial_upgrade_to_pro_shown"
const val WAS_APP_ICON_CUSTOMIZATION_WARNING_SHOWN = "was_app_icon_customization_warning_shown"
const val APP_SIDELOADING_STATUS = "app_sideloading_status"
const val DATE_FORMAT = "date_format"
const val WAS_OTG_HANDLED = "was_otg_handled_2"
const val WAS_UPGRADED_FROM_FREE_SHOWN = "was_upgraded_from_free_shown"
const val WAS_RATE_US_PROMPT_SHOWN = "was_rate_us_prompt_shown"
const val WAS_APP_RATED = "was_app_rated"
const val WAS_SORTING_BY_NUMERIC_VALUE_ADDED = "was_sorting_by_numeric_value_added"
const val WAS_FOLDER_LOCKING_NOTICE_SHOWN = "was_folder_locking_notice_shown"
const val LAST_RENAME_USED = "last_rename_used"
const val LAST_RENAME_PATTERN_USED = "last_rename_pattern_used"
const val LAST_EXPORTED_SETTINGS_FOLDER = "last_exported_settings_folder"
const val LAST_EXPORTED_SETTINGS_FILE = "last_exported_settings_file"
const val LAST_BLOCKED_NUMBERS_EXPORT_PATH = "last_blocked_numbers_export_path"
const val BLOCK_UNKNOWN_NUMBERS = "block_unknown_numbers"
const val BLOCK_HIDDEN_NUMBERS = "block_hidden_numbers"
const val FONT_SIZE = "font_size"
const val WAS_MESSENGER_RECORDER_SHOWN = "was_messenger_recorder_shown"
const val DEFAULT_TAB = "default_tab"
const val START_NAME_WITH_SURNAME = "start_name_with_surname"
const val FAVORITES = "favorites"
const val SHOW_CALL_CONFIRMATION = "show_call_confirmation"
const val COLOR_PICKER_RECENT_COLORS = "color_picker_recent_colors"
const val SHOW_CONTACT_THUMBNAILS = "show_contact_thumbnails"
const val SHOW_PHONE_NUMBERS = "show_phone_numbers"
const val SHOW_ONLY_CONTACTS_WITH_NUMBERS = "show_only_contacts_with_numbers"
const val IGNORED_CONTACT_SOURCES = "ignored_contact_sources_2"
const val LAST_USED_CONTACT_SOURCE = "last_used_contact_source"
const val ON_CONTACT_CLICK = "on_contact_click"
const val SHOW_CONTACT_FIELDS = "show_contact_fields"
const val SHOW_TABS = "show_tabs"
const val SHOW_DIALPAD_BUTTON = "show_dialpad_button"
const val SPEED_DIAL = "speed_dial"
const val LAST_EXPORT_PATH = "last_export_path"
const val WAS_LOCAL_ACCOUNT_INITIALIZED = "was_local_account_initialized"
const val SHOW_PRIVATE_CONTACTS = "show_private_contacts"
const val MERGE_DUPLICATE_CONTACTS = "merge_duplicate_contacts"
const val FAVORITES_CONTACTS_ORDER = "favorites_contacts_order"
const val FAVORITES_CUSTOM_ORDER_SELECTED = "favorites_custom_order_selected"
const val VIEW_TYPE = "view_type"
const val CONTACTS_GRID_COLUMN_COUNT = "contacts_grid_column_count"
const val AUTO_BACKUP = "auto_backup"
const val AUTO_BACKUP_FOLDER = "auto_backup_folder"
const val AUTO_BACKUP_FILENAME = "auto_backup_filename"
const val LAST_AUTO_BACKUP_TIME = "last_auto_backup_time"



const val DATE_FORMAT_ONE = "dd.MM.yyyy"
const val DATE_FORMAT_TWO = "dd/MM/yyyy"
const val DATE_FORMAT_THREE = "MM/dd/yyyy"
const val DATE_FORMAT_FOUR = "yyyy-MM-dd"
const val DATE_FORMAT_FIVE = "d MMMM yyyy"
const val DATE_FORMAT_SIX = "MMMM d yyyy"
const val DATE_FORMAT_SEVEN = "MM-dd-yyyy"
const val DATE_FORMAT_EIGHT = "dd-MM-yyyy"
const val DATE_FORMAT_NINE = "yyyyMMdd"
const val DATE_FORMAT_TEN = "yyyy.MM.dd"
const val DATE_FORMAT_ELEVEN = "yy-MM-dd"
const val DATE_FORMAT_TWELVE = "yyMMdd"
const val DATE_FORMAT_THIRTEEN = "yy.MM.dd"
const val DATE_FORMAT_FOURTEEN = "yy/MM/dd"

const val TIME_FORMAT_12 = "hh:mm a"
const val TIME_FORMAT_24 = "HH:mm"


val normalizeRegex = "\\p{InCombiningDiacriticalMarks}+".toRegex()

val photoExtensions: Array<String> get() = arrayOf(".jpg", ".png", ".jpeg", ".bmp", ".webp", ".heic", ".heif", ".apng", ".avif")
val videoExtensions: Array<String> get() = arrayOf(".mp4", ".mkv", ".webm", ".avi", ".3gp", ".mov", ".m4v", ".3gpp")
val audioExtensions: Array<String> get() = arrayOf(".mp3", ".wav", ".wma", ".ogg", ".m4a", ".opus", ".flac", ".aac", ".m4b")
val rawExtensions: Array<String> get() = arrayOf(".dng", ".orf", ".nef", ".arw", ".rw2", ".cr2", ".cr3")

val extensionsSupportingEXIF: Array<String> get() = arrayOf(".jpg", ".jpeg", ".png", ".webp", ".dng")

val DARK_GREY = 0xFF333333.toInt()


// possible icons at the top left corner
enum class NavigationIcon(@StringRes val accessibilityResId: Int) {
    Cross(R.string.camlib_close),
    Arrow(R.string.camlib_back),
    None(0)
}


const val LOWER_ALPHA = 0.25f
const val MEDIUM_ALPHA = 0.5f
const val HIGHER_ALPHA = 0.75f

// alpha values on a scale 0 - 255
const val LOWER_ALPHA_INT = 30
const val MEDIUM_ALPHA_INT = 90

const val REAL_FILE_PATH = "real_file_path_2"
