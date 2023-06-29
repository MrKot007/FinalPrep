package com.example.session_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.example.session_2.databinding.ActivityVerirficationActvityBinding

class VerirficationActvity : AppCompatActivity() {
    private lateinit var binding: ActivityVerirficationActvityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerirficationActvityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val resendText = "<font color=#a7a7a7>If you didn't receive code,</font> <font color=#0560fa>Resend</font>"
        binding.resend.setText(Html.fromHtml(resendText, Html.FROM_HTML_MODE_COMPACT))
        binding.resend.setOnClickListener {
            finish()
        }
        binding.setPassword.setOnClickListener {
            startActivity(Intent(this@VerirficationActvity, NewPasswordActivity::class.java))
        }
    }
}