package com.example.proyectozoomx.usescases


<<<<<<< HEAD
import com.example.proyectozoomx.entities.*
=======
import com.example.proyectozoomx.entities.Credenciales
import com.example.proyectozoomx.entities.Rol
import com.example.proyectozoomx.entities.Sala
import com.example.proyectozoomx.entities.Usuario
>>>>>>> c4d4aa4670665dc7bd5728fdf76e2d35a51ebc9e
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
<<<<<<< HEAD
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

=======
import okhttp3.OkHttpClient
import okhttp3.Request
>>>>>>> c4d4aa4670665dc7bd5728fdf76e2d35a51ebc9e
import org.json.JSONObject
import java.time.LocalDateTime

// comentario de prueba

class ClientZoomApi(

    private val urlApi: String
) : ZoomApi {
<<<<<<< HEAD
    override suspend fun send( usuario: Credenciales): Usuario =
=======
    override suspend fun send(credenciales: Credenciales): Usuario =
>>>>>>> c4d4aa4670665dc7bd5728fdf76e2d35a51ebc9e
        withContext(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(urlApi+"/usuario")
    //            .header("Authorization", Credentials.basic(usuario.username, usuario.password))
                .build()
            val response = client.newCall(request).execute()
            if (response.code == 200) {
                val body = response.body!!.string()
                response.close()
                return@withContext toUsuario(JSONObject(body))
            } else {
                return@withContext Usuario(
                    usuario.username,
                    Rol.INVALID
                ) //TODO AGREGAR EL ROL DE INVALID A EL ADMIN QUE PUSE PARA QUE NO EXPLOTE
            }
        }

    override suspend fun buscarPorId(id: Int): Sala =
        withContext(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(urlApi + "/sala/$id")
                .header("Authorization", Credentials.basic(usuario.username, usuario.password))
                .build()
            val response = client.newCall(request).execute()
            val body = response.body!!.string()
            response.close()
            return@withContext toSala(JSONObject(body))
        }

    override suspend fun buscarPorNombre(nombre: String): List<Sala> =
        withContext(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(urlApi + "/sala/search/findAllByNombreContains?nombre=$nombre")
                .header("Authorization", Credentials.basic(usuario.username, usuario.password))
                .build()
            val response = client.newCall(request).execute()
            val body = response.body!!.string()
            response.close()
            return@withContext toSalasList(JSONObject(body))


        }

<<<<<<< HEAD
    override suspend fun buscarPorResponsable(responsable: String): List<Sala> =
        withContext(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(urlApi + "/sala/search/findAllByResponsableContains?responsable=$responsable")
                .header("Authorization", Credentials.basic(usuario.username, usuario.password))
=======
    override suspend fun buscarPorId(id: Int): Sala =
        withContext(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(urlApi+"/sala/$id")
               // .header("Authorization", Credentials.basic(usuario.username, usuario.password))
>>>>>>> c4d4aa4670665dc7bd5728fdf76e2d35a51ebc9e
                .build()
            val response = client.newCall(request).execute()
            val body = response.body!!.string()
            response.close()
<<<<<<< HEAD
            return@withContext toSalasList(JSONObject(body))


=======
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
>>>>>>> c4d4aa4670665dc7bd5728fdf76e2d35a51ebc9e
        }

    override suspend fun buscarPorFecha(fecha: LocalDateTime): List<Sala> =
        withContext(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(urlApi + "/sala/search/findAllByFechaDeReserva?fechaDeReserva=$fecha")
                .header("Authorization", Credentials.basic(usuario.username, usuario.password))
                .build()
            val response = client.newCall(request).execute()
            val body = response.body!!.string()
            response.close()
            return@withContext toSalasList(JSONObject(body))
        }

    override suspend fun ingresarSala(usuario: Credenciales,sala: Sala) =
        withContext(Dispatchers.IO) {
            val client = OkHttpClient()
            val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()
            val body = convertSalaToJSON(sala).toString().toRequestBody(JSON)
            val request = Request.Builder()
                .url(urlApi + "/sala")
                .header("Authorization", Credentials.basic(usuario.username, usuario.password))
                .post(body).build()
            val response = client.newCall(request).execute()
            response.close()

        }

    override suspend fun borrarSala(id: Int) =
        withContext(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(urlApi + "/sala/$id")
                .header("Authorization", Credentials.basic(usuario.username, usuario.password))
                .delete().build()
            val response = client.newCall(request).execute()
            response.close()
        }

    override suspend fun modificarSala(id: Int, sala: Sala) =
        withContext(Dispatchers.IO) {
            val client = OkHttpClient()
            val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()
            val body = convertSalaToJSON(sala).toString().toRequestBody(JSON)
            val request = Request.Builder()
                .url(urlApi + "/sala/$id")
                .header("Authorization", Credentials.basic(usuario.username, usuario.password))
                .patch(body).build()
            val response = client.newCall(request).execute()
            response.close()
    }
<<<<<<< HEAD
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

    return Sala(nombre, responsable, fechaDeReserva, tiempoReservaEnHoras, url)
}

fun toSalasList(body: JSONObject): List<Sala> {
    val salas = ArrayList<Sala>()

    val embedded = body.getJSONObject("_embedded")
    val arraySalas = embedded.getJSONArray("sala")

    for (i in 0 until arraySalas.length()) {
        val jsonSala = arraySalas.getJSONObject(i)
        val nombre = jsonSala.getString("nombre")
        val responsable = jsonSala.getString("responsable")
        val fechaDeReserva = LocalDateTime.parse(jsonSala.getString("fechaDeReserva"))
        val tiempoReservaEnHoras = jsonSala.getInt("tiempoReservaEnHoras")
        val url = jsonSala.getString("icono")
        val sala: Sala = Sala(nombre, responsable, fechaDeReserva, tiempoReservaEnHoras, url)
        salas.add(sala)
    }
    return salas
}

fun convertSalaToJSON(sala: Sala): JSONObject {
    val json = JSONObject()
    json.put("nombre", sala.nombre)
    json.put("responsable", sala.responsable)
    json.put("fechaDeReserva", sala.fechaDeReserva)
    json.put("tiempoReservaEnHoras", sala.tiempoReservaEnHoras)
    json.put("icono", sala.url)

    return json


}

=======
    fun toSala(body: JSONObject): Sala {
        val nombre = body.getString("nombre")
        val responsable = body.getString("responsable")
        val fechaDeReserva = LocalDateTime.parse(body.getString("fechaDeReserva"))
        val tiempoReservaEnHoras = body.getInt("tiempoReservaEnHoras")
        val url = body.getString("icono")

        return Sala(nombre,responsable, fechaDeReserva,tiempoReservaEnHoras,url)
    }
}
>>>>>>> c4d4aa4670665dc7bd5728fdf76e2d35a51ebc9e
