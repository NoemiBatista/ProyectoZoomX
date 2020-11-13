package com.example.proyectozoomx

import android.net.Credentials
import okhttp3.Credentials.basic
import okhttp3.OkHttpClient
import okhttp3.Response
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.Test
import org.junit.runner.Request
import java.io.IOException

class ZoomApiUnitTest {

    @Test
    @Throws(IOException::class)
    fun givenAuthUsuario_whenGetUsuario_thenGetResponseOk(){
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://zoomx.freeddns.org:8443/usuario")
            .header("Authorization", Credentials.basic("adm","adm"))
            .build()

        val rolExpected = "ROLE_ADMIN"
        val response = client.newCall(request).execute()
        val body = response.body!!.string()

        assertSoftly {
            it.assertThat(response.code).isEqualTo(200)
            it.assertThat(body).contains(rolExpected)
        }
        Response.close()
    }
}