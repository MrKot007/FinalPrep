package com.example.session_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.session_4.databinding.ActivityTrackBinding

class TrackActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTrackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toSendPackage.setOnClickListener {
            startActivity(Intent(this@TrackActivity, SendPackageActivity::class.java))
        }
    }
}