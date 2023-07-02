package com.example.thirditeration

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
    suspend fun signUp(fullName: String, phoneNumber: String, email: String, password: String) : Pair<String, Boolean> {
        try {
            client.gotrue.signUpWith(Email) {
                this.email = email
                this.password = password
            }
        }catch (e: HttpRequestException) {
            return Pair("Проверьте подключение к интернету", false)
        }catch (e: BadRequestRestException) {
            return Pair(e.error, false)
        }catch (e: Exception) {
            return Pair(e.message ?: "", false)
        }
        return Pair("", true)
    }
    suspend fun loginWithEmail(email: String, password: String) : Pair<String, Boolean> {
        try {
            client.gotrue.loginWith(Email) {
                this.email = email
                this.password = password
            }
        }catch (e: HttpRequestException) {
            return Pair("Проверьте подключение к интернету", false)
        }catch (e: BadRequestRestException) {
            return Pair(e.error, false)
        }catch (e: Exception) {
            return Pair(e.message ?: "", false)
        }
        return Pair("", true)
    }
    suspend fun loginViaGoogle() : Pair<String, Boolean> {
        try {
            client.gotrue.loginWith(Google) {
                queryParams["access_type"] = "offline"
                queryParams["prompt"] = "consent"
            }
        }catch (e: HttpRequestException) {
            return Pair("Проверьте подключение к интернету", false)
        }catch (e: BadRequestRestException) {
            return Pair(e.error, false)
        }catch (e: Exception) {
            return Pair(e.message ?: "", false)
        }
        return Pair("", true)
    }
    suspend fun collectCallback(callback: SupabaseCallback) {
        client.gotrue.sessionStatus.collect {
            when(it) {
                is SessionStatus.Authenticated -> {
                    callback.onAuth(it.session.user)
                }
                SessionStatus.NotAuthenticated -> {
                    callback.unauthorizedEntry()
                }
                SessionStatus.NetworkError -> {
                    callback.onNetworkError()
                }
                SessionStatus.LoadingFromStorage -> {
                    callback.onLoading()
                }
            }
        }
    }
}
interface SupabaseCallback {

    fun onAuth(user: UserInfo?)

    fun onLoading()

    fun onNetworkError()

    fun unauthorizedEntry()


}