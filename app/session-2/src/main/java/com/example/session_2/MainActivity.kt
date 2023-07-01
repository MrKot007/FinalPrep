package com.example.session_2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.session_2.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (SupabaseConnection.checkAuth()) {
            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
            finish()
        }
        val linkText = "<font color=#a7a7a7>By ticking this box, you agree to our </font> <font color=#ebbc2e>Terms and conditions and private policy</font>"
        binding.link.setText(Html.fromHtml(linkText, Html.FROM_HTML_MODE_COMPACT))
        val signIn = "<font color=#a7a7a7>Already have an account? </font> <font color=#0560fa>Sign in</font>"
        binding.toSignIn.setText(Html.fromHtml(signIn, Html.FROM_HTML_MODE_COMPACT))
        binding.toSignIn.setOnClickListener {
            startActivity(Intent(this@MainActivity, SignInActivity::class.java))
        }
        binding.signUp.setOnClickListener {
            if (binding.fullName.text != null && binding.phoneNumber.text != null && binding.email.text != null && binding.password.text != null && checkEmailPattern(binding.email.text.toString())) {
                val name = binding.fullName.text.toString()
                val phone = binding.phoneNumber.text.toString()
                val email = binding.email.text.toString()
                val password = binding.password.text.toString()
                lifecycleScope.launch {
                    val result = SupabaseConnection.signUp(name, phone, email, password)
                    if (result.second) {
                        startActivity(Intent(this@MainActivity, SignInActivity::class.java))
                        finish()
                    }else {
                        showAlertDialog(result.first, this@MainActivity)
                    }
                }
            }else {
                Toast.makeText(this@MainActivity, "Not all fields are filled in or an email doesn't match the pattern!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
        }
        binding.loginViaGoogleMain.setOnClickListener {
            lifecycleScope.launch {
                SupabaseConnection.loginWithGoogle()
            }
        }
    }
    private fun checkEmailPattern(email: String) : Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun showAlertDialog(reason: String, context: Context) {
        AlertDialog.Builder(context)
            .setTitle("Ошибка!")
            .setMessage(reason)
            .setPositiveButton("OK", null)
            .create().show()

    }
}