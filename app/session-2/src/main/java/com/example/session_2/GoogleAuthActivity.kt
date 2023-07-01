package com.example.session_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class GoogleAuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_auth)
        val url = intent.data.toString()
        val params = url.substringAfter("#")
        val mapParams = params.split("&").map { it.split("=") }.associate { it[0] to it[1] }
        lifecycleScope.launch {
            SupabaseConnection.loginWithGoogle()
            startActivity(Intent(this@GoogleAuthActivity, HomeActivity::class.java))
            finish()
        }
    }
}