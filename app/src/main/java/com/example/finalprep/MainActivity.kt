package com.example.finalprep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.finalprep.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val controller = Controller()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val elements = listOf<Pair<String, Int>>(Pair("first", R.drawable.boarding1), Pair("second", ))
    }
}