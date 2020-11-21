package com.example.proyectozoomx.usescases


import com.example.proyectozoomx.entities.*
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.Credentials
import kotlinx.coroutines.withContext
import okhttp3.*

import org.json.JSONObject
import java.time.LocalDateTime

// comentario de prueba

class ClientZoomApi(
    private val usuario: Credenciales,
    private val urlApi: String
) : ZoomApi {
    override suspend fun send(): Usuario =
        withContext(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(urlApi)
                .header("Authorization", Credentials.basic(usuario.username, usuario.password))
                .build()
            val response = client.newCall(request).execute()
            val body = response.body!!.string()
            response.close()
            return@withContext toUsuario(JSONObject(body))
        }

    override suspend fun buscarPorId(id: Int): Sala =
        withContext(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(urlApi+"/sala/$id")
                .header("Authorization", Credentials.basic(usuario.username, usuario.password))
                .build()
            val response = client.newCall(request).execute()
            val body = response.body!!.string()
            response.close()
            return@withContext toSala(JSONObject(body))
        }


    fun toUsuario(body: JSONObject): Usuario {
        val username = body.getString("username")
        val authorities = body.getJSONArray("authorities")
        val rolAsString = authorities.getJSONObject(0).getString("authority")
        val rol = if (rolAsString == "ROLE_ADMIN") {
            Rol.ADMIN
        } else {
            Rol.USER
        }
        return Usuario(username, rol)
    }
    fun toSala(body: JSONObject): Sala {
        val nombre = body.getString("nombre")
        val responsable = body.getString("responsable")
        val fechaDeReserva = LocalDateTime.parse(body.getString("fechaDeReserva"))
        val tiempoReservaEnHoras = body.getInt("tiempoReservaEnHoras")
        val url = body.getString("icono")

        return Sala(nombre,responsable, fechaDeReserva,tiempoReservaEnHoras,url)
    }
}