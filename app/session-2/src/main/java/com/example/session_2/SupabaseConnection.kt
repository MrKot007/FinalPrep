package com.example.session_2

import android.util.Log
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.exceptions.BadRequestRestException
import io.github.jan.supabase.exceptions.HttpRequestException
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.user.UserInfo
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object SupabaseConnection {
    val client = createSupabaseClient(
        "https://fuzepnebwkauqwrhccpz.supabase.co",
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZ1emVwbmVid2thdXF3cmhjY3B6Iiwicm9sZSI6ImFub24iLCJpYXQiOjE2ODc4MDk1NzEsImV4cCI6MjAwMzM4NTU3MX0.OtZywZhSuUz3dVtec7zH_53JkBDmcyCYgMaJ8vNxTIs"
    ) {
        install(Postgrest)
        install(GoTrue)
        install(Storage)
    }
    suspend fun loginWithEmail(email: String, password: String) {
        client.gotrue.loginWith(Email) {
            this.email = email
            this.password = password
        }
    }
    suspend fun loginWithGoogle() {
        client.gotrue.loginWith(Google) {
            queryParams["access_type"] = "offline"
            queryParams["prompt"] = "consent"
        }
    }
    suspend fun signUp(fullName: String, phone: String, email: String, password: String) : Pair<String, Boolean> {
        try {
            client.gotrue.signUpWith(Email) {
                this.email = email
                this.password = password
            }
        }catch(e: HttpRequestException) {
            Log.e("signUpError", e.stackTraceToString())
            return Pair("проверьте подключение к интернету!", false)
        }catch (e: BadRequestRestException) {
            Log.e("signUpError", e.stackTraceToString())
            return Pair(e.error, false)
        }catch (e:Exception) {
            Log.e("errorSignUp", e.stackTraceToString())
            return Pair(e.message ?: "", false)
        }
        return Pair("", true)
    }

    suspend fun collectCallback(callback: SupabaseConnectionCallback) {
        client.gotrue.sessionStatus.collect {
            when(it) {
                is SessionStatus.Authenticated -> {
                    callback.onAuth(it.session.user)
                }
                SessionStatus.LoadingFromStorage -> {
                    callback.onLoadingFromStorage()
                }
                SessionStatus.NetworkError -> {
                    callback.onNetworkError()
                }
                SessionStatus.NotAuthenticated -> {
                    callback.unauthorizedEntry()
                }
            }
        }
    }
    fun checkAuth() : Boolean {
        val status = client.gotrue.sessionStatus.value
        return status is SessionStatus.Authenticated
    }

}
interface SupabaseConnectionCallback {
    fun onAuth(user: UserInfo?)

    fun onNetworkError()

    fun onLoadingFromStorage()

    fun unauthorizedEntry()
}