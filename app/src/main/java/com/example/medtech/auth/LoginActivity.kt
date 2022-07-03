package com.example.medtech.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.medtech.R

class LoginActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

//        //Make status bar white
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            val window: Window = window
//            val decorView: View = window.decorView
//            val wic = WindowInsetsControllerCompat(window, decorView)
//            wic.isAppearanceLightStatusBars = true
//            window.statusBarColor = Color.WHITE
//        }
    }
}