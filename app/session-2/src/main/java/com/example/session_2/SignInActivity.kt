package com.example.session_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.example.session_2.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val signUpText = "<font color=#a7a7a7>Don't have an account yet? </font> <font color=#0560fa>Sign Up</font>"
        binding.toSignUp.setText(Html.fromHtml(signUpText, Html.FROM_HTML_MODE_COMPACT))
        binding.forgot.setOnClickListener {
            startActivity(Intent(this@SignInActivity, ForgotPasswordActivity::class.java))
        }
        binding.toSignUp.setOnClickListener {
            finish()
        }
        binding.logIn.setOnClickListener {
            startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
        }
    }
}