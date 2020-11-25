package com.example.proyectozoomx.usescases


import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.proyectozoomx.entities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import okhttp3.OkHttpClient
import okhttp3.Credentials
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray

import org.json.JSONObject
import java.time.LocalDateTime

// comentario de prueba

abstract class ClientZoomApi(
    private val usuario: Credenciales,
    private val urlApi: String
) : ZoomApi {

    //LOGIN
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

    //INGRESO DE SALA
     suspend fun ingresarSala(usuario: Credenciales, sala: Sala): Unit =
        withContext(Dispatcher.IO) {
            val client = OkHttpClient()
            val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()
            //val Json = ""
            val body = convertirSalaIngreso(sala)
            val request: Request = Request.Builder()
                .url("http://localhost:8080/sala")
                .header("Authorization", Credentials.basic(usuario.username, usuario.password))
                .post(body).build()
            val response = client.newCall(request).execute()

        }

    fun convertirSalaIngreso(sala: Sala): JSONObject {

        val jSON = JSONObject()
        jSON.put("nombre", sala.nombre)
        jSON.put("responsable", sala.responsable)
        jSON.put("fecha_Reserva", sala.fechaDeReserva)
        jSON.put("tiempo_reservas_Hs", sala.tiempoReservaEnHoras)
        jSON.put("icono", sala.url)

        return jSON

    }

    //BUSQUEDA DE SALA POR NOMBRE
    override suspend fun buscarSala(usuario: Credenciales, nombre: String): List<Sala> =
        withContext(Dispatchers.IO) {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url(urlApi + "http://localhost:8080/sala/search/findAllByNombreContains?nombre=" + nombre)
                .header("Authorization", Credentials.basic(usuario.username, usuario.password))
                .delete().build()
            val response = client.newCall(request).execute()
            if (response.code == 200) {
                val body = response.body!!.string()
                Log.d("nombre", body + usuario)
                response.close()
                return@withContext convertirSala(JSONObject(body))
            } else {
                return@withContext convertirSala(JSONObject(""))
            }
        }

    //CONVERTIR SALA
    fun convertirSala(body: JSONObject): List<Sala> {

        val salas: ArrayList<Sala>()
        val embedded: JSONObject = body.getJSONObject("_embedded")
        val arraySala: JSONArray = embedded.getJSONArray("sala")

        for (i: Int in 0 until arraySala.length()) {

            val jsonSala: JSONObject = arraySala.getJSONObject(i)
            val nombre: String = jsonSala.getString("nombr")
            val responsable: String = jsonSala.getString("responsabl")
            val fechaReserva: String = jsonSala.getString("fechaReserv")
            val tiempoReserva: String = jsonSala.getString("tiempoReserv")
            val iconoURL: String = jsonSala.getString("iconoUrl")
            val sala: Sala = Sala(
                nombre, responsable,
                LocalDateTime.parse(fechaReserva),
                tiempoReserva.toInt(), iconoURL)

            salas.add(sala)
        }
        return salas
    }
}
/*
        //BUSCAR POR ID
        suspend fun buscarPorId(id: Int): Sala =
            withContext(Dispatchers.IO) {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url(urlApi + "/sala/$id")
                    .header("Authorization", Credentials.basic(usuario.username, usuario.password))
                    .build()
                val response = client.newCall(request).execute()
                val body = response.body!!.string()
                response.close()
                return@withContext convertirSalaBusquedaID(JSONObject(body))
            }


        fun convertirSalaBusquedaID(body: JSONObject): Sala {
            val nombre = body.getString("nombre")
            val responsable = body.getString("responsable")
            val fechaDeReserva = LocalDateTime.parse(body.getString("fechaDeReserva"))
            val tiempoReservaEnHoras = body.getInt("tiempoReservaEnHoras")
            val url = body.getString("icono")

            return Sala(nombre, responsable, fechaDeReserva, tiempoReservaEnHoras, url)
        }
*/