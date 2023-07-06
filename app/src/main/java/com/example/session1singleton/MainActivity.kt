package com.example.session1singleton

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import com.example.session1singleton.Queue.controller
import com.example.session1singleton.databinding.ActivityMainBinding

object Queue {
    val controller = Controller()
}
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shared = getSharedPreferences("main", Context.MODE_PRIVATE)
        val element = controller.getElement()
        binding.boardingImage.setImageResource(element.third)
        binding.heading.text = element.first
        binding.paragraph.text = element.second

        binding.next.setOnClickListener {
            controller.deleteElement()
            if (controller.getSize() == 1) {
                binding.next.visibility = View.GONE
                binding.skip.visibility = View.GONE
                binding.toSignUp.visibility = View.VISIBLE
                val signInText = "<font color=#a7a7a7>Already have an account? </font> <font color=#0560fa>Sign in</font>"
                binding.toSignIn.setText(Html.fromHtml(signInText, Html.FROM_HTML_MODE_COMPACT))
                binding.toSignUp.setOnClickListener {
                    shared.edit().putBoolean("isFirstEnter", false).apply()
                    startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                    finish()
                }
            }
            val element = controller.getElement()
            binding.boardingImage.setImageResource(element.third)
            binding.heading.text = element.first
            binding.paragraph.text = element.second
        }
        binding.skip.setOnClickListener {
            shared.edit().putBoolean("isFirstEnter", false).apply()
            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
            finish()
        }

    }
}