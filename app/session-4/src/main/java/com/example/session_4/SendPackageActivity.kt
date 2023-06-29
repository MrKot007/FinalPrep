package com.example.session_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.session_4.databinding.ActivitySendPackageBinding

class SendPackageActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySendPackageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendPackageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener {
            finish()
        }
    }
}