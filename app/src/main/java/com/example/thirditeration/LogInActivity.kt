package com.example.thirditeration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.InputType
import androidx.lifecycle.lifecycleScope
import com.example.thirditeration.databinding.ActivityLogInBinding
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.coroutines.launch
import org.slf4j.helpers.Util

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val signInText = "<font color=#a7a7a7>Already have an account?</font> <font color=#0560fa>Sign in</font>"
        binding.toSignUp.setText(Html.fromHtml(signInText, Html.FROM_HTML_MODE_COMPACT))
        binding.eyeLogIn.setOnClickListener {
            val inpType = binding.passLogIn.inputType
            if (inpType != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                binding.passLogIn.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }else {
                binding.passLogIn.inputType = InputType.TYPE_CLASS_TEXT + InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }
        binding.loginViaGoogleSignIn.setOnClickListener {
            lifecycleScope.launch {
                SupabaseConnection.loginViaGoogle()
            }
        }
        binding.login.setOnClickListener {
            if (binding.emailLogIn.text.toString() != "" && binding.passLogIn.text.toString() != "") {
                val email = binding.emailLogIn.text.toString()
                val pass = binding.passLogIn.text.toString()
                lifecycleScope.launch {
                    SupabaseConnection.loginWithEmail(email, pass)
                    SupabaseConnection.collectCallback(object: SupabaseCallback{
                        override fun onAuth(user: UserInfo?) {
                            startActivity(Intent(this@LogInActivity, HomeActivity::class.java))
                            finish()
                        }

                        override fun onLoading() {}

                        override fun onNetworkError() {
                            Utils.showAlertDialog(this@LogInActivity, "Check your internet connection")
                        }

                        override fun unauthorizedEntry() {
                            Utils.showAlertDialog(this@LogInActivity, "Given data is incorrect")
                        }
                    })
                }

            }else {
                Utils.showAlertDialog(this@LogInActivity, "Not all the fields are filled in")
                return@setOnClickListener
            }
        }

    }
}