package com.sverbusoft.lifeflowerpot.utils.helpers

import android.content.Context
import android.content.SharedPreferences
import com.sverbusoft.lifeflowerpot.App

class SharedPrefHelper {

    companion object {
        private const val FILE = "SharedPreferences"
        private const val EMAIL_USER_DATA: String = "userData";

        private fun getPreferences(): SharedPreferences = App.getContext().getSharedPreferences(FILE, Context.MODE_PRIVATE)

        fun getPreviousUserData(): String = getPreferences().getString(EMAIL_USER_DATA, "")

        fun setUserData(value: String) {
            getPreferences().edit().putString(EMAIL_USER_DATA, value).apply()
        }
    }
}