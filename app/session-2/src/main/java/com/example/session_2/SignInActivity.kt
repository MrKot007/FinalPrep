package com.example.session_2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.session_2.databinding.ActivitySignInBinding
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.coroutines.launch

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
            val email = binding.authEmail.text.toString()
            val password = binding.authPassword.text.toString()
            lifecycleScope.launch {
                SupabaseConnection.loginWithEmail(email, password)
                SupabaseConnection.collectCallback(object: SupabaseConnectionCallback{
                    override fun onAuth(user: UserInfo?) {
                        startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
                        finish()
                    }

                    override fun onNetworkError() {
                        showAlertDialog("Вход не выполнен, проверьте подключение к интернету!", this@SignInActivity)
                    }

                    override fun onLoadingFromStorage() {}

                    override fun unauthorizedEntry() {
                        showAlertDialog("Вход не выполнен, данные некорректны!", this@SignInActivity)
                    }
                })
            }
        }
        binding.loginViaGoogle.setOnClickListener {
            lifecycleScope.launch {
                SupabaseConnection.loginWithGoogle()
            }
        }
    }
    private fun showAlertDialog(reason: String, context: Context) {
        AlertDialog.Builder(context)
            .setTitle("Ошибка!")
            .setMessage(reason)
            .setPositiveButton("OK", null)
            .create().show()
    }
}