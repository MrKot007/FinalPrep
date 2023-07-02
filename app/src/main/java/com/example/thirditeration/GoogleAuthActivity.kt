package com.example.thirditeration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import io.github.jan.supabase.gotrue.gotrue
import kotlinx.coroutines.launch

class GoogleAuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_auth)
        val url = intent.data.toString()
        val params = url.substringAfter("#")
        val mapParams = params.split("&").map { it.split("=") }.associate { it[0] to it[1] }
        lifecycleScope.launch {
            SupabaseConnection.client.gotrue.importAuthToken(mapParams["access_token"]!!, mapParams["refresh_token"]!!)
            startActivity(Intent(this@GoogleAuthActivity, HomeActivity::class.java))
        }
    }
}