package com.example.session_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.example.session_2.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val backText = "<font color=#a7a7a7>Remember password? Back to </font> <font color=#0560fa>Sign in</font>"
        binding.backToSignIn.setText(Html.fromHtml(backText, Html.FROM_HTML_MODE_COMPACT))
        binding.backToSignIn.setOnClickListener {
            finish()
        }
        binding.sendOTP.setOnClickListener {
            startActivity(Intent(this@ForgotPasswordActivity, VerirficationActvity::class.java))
        }
    }
}