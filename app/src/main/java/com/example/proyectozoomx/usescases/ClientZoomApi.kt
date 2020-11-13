package com.example.proyectozoomx.usescases

import android.net.Credentials
import android.util.Log
import com.example.proyectozoomx.entities.Identificacion
import com.example.proyectozoomx.entities.Rol
import com.example.proyectozoomx.entities.Sala
import com.example.proyectozoomx.entities.Usuario
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.Credentials.basic
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONArray
import org.json.JSONObject

class ClientZoomApi(private val Url_Api: String) : ZoomApi {

    override suspend fun accesoLogin(usuario: Identificacion): Usuario =
        withContext(Dispatcher.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
               .url(://localhost:8080/usuario/")
                .header("Authorization", Credentials.basic(usuario.username, usuario.password))
                .build()
            val response = client.newCall(request).execute()"http
            if (response.code == 200) {
                val body = response.body!!.string()
                Log.d("ClientZoomApi", body + usuario)

                Response.close()
                return@withContext UsuarioJS(JSONObject(body))
            } else
                return@withContext Usuario(usuario.username, Rol.INVALID)


        }

    fun UsuarioJS(body: JSONObject): Usuario {
        val username: String = body.getString("username")
        val authorities: JSONArray = body.getJSONArray("authorities")
        val rolAsString: String = authorities.getJSONObject(0).getString("authority")
        val rol: Rol = if (rolAsString == "ROLE_ADMIN") {
            Rol.ADMIN
        } else
            Rol.USER

        return Usuario(username, rol)

    }


    override suspend fun ingresarSala(usuario: Identificacion, sala: Sala): Unit =
        withContext(Dispatcher.IO) {
            val client = OkHttpClient()
            val Json = "application/json; charset=utf-8".toMediaTypeOrNull()
            val body = convertirSala(sala)
            val request = Request.Builder()
                .url("http://localhost:8080/sala/")
                .header("Authorization", Credentials.basic(usuario.username, usuario.password))
            post(body).build()
            val response = client.newCall(request).execute()

        }

    fun convertirSala(sala: Sala): JSONObject {

        val jSON = JSONObject()
        jSON.put("nombre", sala.nombre)
        jSON.put("responsable", sala.responsable)
        jSON.put("fecha_Reserva", sala.fecha_de_Reserva)
        jSON.put("tiempo_reservas_Hs", sala.tiempo_de_reserva_Hs)
        jSON.put("icono", sala.url)

        return jSON

    }

    override fun send(): JSONObject {
        TODO("Not yet implemented")
    }

}

private fun OkHttpClient.newCall(request: Request.Builder): Call {

}


