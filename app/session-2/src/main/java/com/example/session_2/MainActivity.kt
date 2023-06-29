package com.example.session_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import com.example.session_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val linkText = "<font color=#a7a7a7>By ticking this box, you agree to our </font> <font color=#ebbc2e>Terms and conditions and private policy</font>"
        binding.link.setText(Html.fromHtml(linkText, Html.FROM_HTML_MODE_COMPACT))
        val signIn = "<font color=#a7a7a7>Already have an account? </font> <font color=#0560fa>Sign in</font>"
        binding.toSignIn.setText(Html.fromHtml(signIn, Html.FROM_HTML_MODE_COMPACT))
        binding.toSignIn.setOnClickListener {
            startActivity(Intent(this@MainActivity, SignInActivity::class.java))
        }
        binding.signUp.setOnClickListener {
            if (binding.fullName.text != null && binding.phoneNumber.text != null && binding.email.text != null && binding.password.text != null && checkEmailPattern(binding.email.text.toString())) {
                startActivity(Intent(this@MainActivity, SignInActivity::class.java))
            }else {
                Toast.makeText(this@MainActivity, "Not all fields are filled in or an email doesn't match the pattern!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

        }
    }
    private fun checkEmailPattern(email: String) : Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}