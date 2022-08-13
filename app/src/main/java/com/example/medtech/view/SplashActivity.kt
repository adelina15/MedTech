package com.example.medtech.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import androidx.core.view.WindowInsetsControllerCompat
import com.example.medtech.view.auth.LoginActivity
import com.example.medtech.view.main.MainActivity
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Make status bar white
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val window: Window = window
            val decorView: View = window.decorView
            val wic = WindowInsetsControllerCompat(window, decorView)
            wic.isAppearanceLightStatusBars = true
            window.statusBarColor = Color.WHITE
        }
//        if (FirebaseAuth.getInstance() == null){
            startActivity(Intent(this, LoginActivity::class.java))
//        } else startActivity(Intent(this, MainActivity::class.java))
        // close this activity
        finish()
    }
}