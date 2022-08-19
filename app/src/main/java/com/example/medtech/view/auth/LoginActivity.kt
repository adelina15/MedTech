package com.example.medtech.view.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.medtech.R
import com.google.firebase.messaging.FirebaseMessaging

//import com.example.medtech.utils.MyFirebaseMessagingService
//import com.google.firebase.iid.FirebaseInstanceId
//import com.google.firebase.messaging.FirebaseMessaging

class LoginActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        FirebaseMessaging.getInstance().token.addOnCompleteListener(this) { instanceIdResult ->
            // Just use this call
            val newToken = instanceIdResult.result
            Log.i("newToken", newToken)
        }
    }
}