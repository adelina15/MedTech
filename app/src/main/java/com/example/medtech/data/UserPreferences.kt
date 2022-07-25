package com.example.medtech.data

import android.content.Context
import android.content.SharedPreferences
import com.example.medtech.utils.Constants

class UserPreferences (context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("TestApp", Context.MODE_PRIVATE)
    private val editor = prefs.edit()

    fun saveUserId(token: Int?){
        if (token != null){
            editor.putInt(Constants.TOKEN, token).apply()
        }
    }

    fun fetchUserId():Int {
        return prefs.getInt(Constants.TOKEN, 1)
    }
}
