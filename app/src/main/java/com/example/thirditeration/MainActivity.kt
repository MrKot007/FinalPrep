package com.example.thirditeration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.InputType
import androidx.lifecycle.lifecycleScope
import com.example.thirditeration.databinding.ActivityMainBinding
import io.github.jan.supabase.gotrue.gotrue
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val policyText = "<font color=#a7a7a7>By ticking this box, you agree to our </font> <font color=#ebbc2e>Terms and conditions and private policy</font>"
        binding.policyText.setText(Html.fromHtml(policyText, Html.FROM_HTML_MODE_COMPACT))
        val signUpText = "<font color=#a7a7a7>Already have an account?</font> <font color=#0560fa>Sign in</font>"
        binding.toSignIn.setText(Html.fromHtml(signUpText, Html.FROM_HTML_MODE_COMPACT))

        binding.toSignIn.setOnClickListener {
            startActivity(Intent(this@MainActivity, LogInActivity::class.java))
            finish()
        }
        binding.eye.setOnClickListener {
            val inpType = binding.pass.inputType
            if (inpType != InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                binding.pass.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }else {
                binding.pass.inputType = InputType.TYPE_CLASS_TEXT + InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }
        binding.signUp.setOnClickListener {
            if (binding.name.text.toString() != "" && binding.phone.text.toString() != "" && binding.email.text.toString() != "" && binding.pass.text.toString() != "" && binding.email.text.toString().lowercase() == binding.email.text.toString() && binding.agree.isChecked) {
                lifecycleScope.launch {
                    val email = binding.email.text.toString()
                    val password = binding.pass.text.toString()
                    val phone = binding.phone.text.toString()
                    val fullName = binding.name.text.toString()
                    val result = SupabaseConnection.signUp(fullName, phone, email, password)
                    if (result.second) {
                        startActivity(Intent(this@MainActivity, LogInActivity::class.java))
                        finish()
                    }else {
                        Utils.showAlertDialog(this@MainActivity, result.first)
                    }
                }
            }else {
                Utils.showAlertDialog(this@MainActivity, "Not all the fileds are filled in or an email doesn't match the pattern or you haven't agreed with privacy policy")
                return@setOnClickListener
            }
        }
        binding.logjnViaGoogle.setOnClickListener {
            lifecycleScope.launch {
                SupabaseConnection.loginViaGoogle()
            }
        }
    }
}