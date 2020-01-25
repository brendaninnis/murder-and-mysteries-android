
package ca.brendaninnis.murdermysteries.utils

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

class PreferenceRepository(private val sharedPreferences: SharedPreferences) {

  val userId: String
    get() {
      sharedPreferences.getString(PREFERENCE_USER_ID, null)?.let {
        return it
      }
      UUID.randomUUID().toString().let {
        sharedPreferences.edit().putString(PREFERENCE_USER_ID, it).apply()
        return it
      }
    }

  val nightMode: Int
    get() = sharedPreferences.getInt(PREFERENCE_NIGHT_MODE, PREFERENCE_NIGHT_MODE_DEF_VAL)

  private val _nightModeLive: MutableLiveData<Int> = MutableLiveData()
  val nightModeLive: LiveData<Int>
    get() = _nightModeLive

  var allowNotifications: Boolean = sharedPreferences.getBoolean(PREFERENCE_NOTIFICATIONS, true)
    set(value) {
      sharedPreferences.edit().putBoolean(PREFERENCE_NOTIFICATIONS, value).apply()
    }

  private val _allowNotificationsLive: MutableLiveData<Boolean> = MutableLiveData()
  val allowNotificationsLive: LiveData<Boolean>
    get() = _allowNotificationsLive

  var isDarkTheme: Boolean = false
    get() = nightMode == AppCompatDelegate.MODE_NIGHT_YES
    set(value) {
      sharedPreferences.edit().putInt(PREFERENCE_NIGHT_MODE, if (value) {
        AppCompatDelegate.MODE_NIGHT_YES
      } else {
        AppCompatDelegate.MODE_NIGHT_NO
      }).apply()
      field = value
    }

  private val _isDarkThemeLive: MutableLiveData<Boolean> = MutableLiveData()
  val isDarkThemeLive: LiveData<Boolean>
    get() = _isDarkThemeLive

  private val preferenceChangedListener =
    SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
      when (key) {
        PREFERENCE_NIGHT_MODE -> {
          _nightModeLive.value = nightMode
          _isDarkThemeLive.value = isDarkTheme
        }
        PREFERENCE_NOTIFICATIONS -> {
          _allowNotificationsLive.value = allowNotifications
        }
      }
    }

  init {
    // Init preference LiveData objects.
    _nightModeLive.value = nightMode
    _isDarkThemeLive.value = isDarkTheme
    _allowNotificationsLive.value = allowNotifications

    sharedPreferences.registerOnSharedPreferenceChangeListener(preferenceChangedListener)
  }

  companion object {
    private const val PREFERENCE_USER_ID = "preference_user_id"
    private const val PREFERENCE_NOTIFICATIONS = "preference_notifications"
    private const val PREFERENCE_NIGHT_MODE = "preference_night_mode"
    private const val PREFERENCE_NIGHT_MODE_DEF_VAL = AppCompatDelegate.MODE_NIGHT_NO
  }
}
