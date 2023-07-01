package com.example.session_5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.session_5.databinding.ActivityDriverBinding

class DriverActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDriverBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDriverBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backToDrivers.setOnClickListener {
            finish()
        }
        binding.driverName.text = intent.getStringExtra("name")
        binding.regNum.text = intent.getStringExtra("regNum")
    }
}