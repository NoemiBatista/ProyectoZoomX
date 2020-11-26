package com.example.proyectozoomx.usescases

import com.example.proyectozoomx.entities.Credenciales
import com.example.proyectozoomx.entities.Identificacion
import com.example.proyectozoomx.entities.Sala
import com.example.proyectozoomx.entities.Usuario
import org.json.JSONObject
import java.time.LocalDateTime

interface ZoomApi {
    suspend fun send(usuario:Credenciales): Usuario
    suspend fun buscarPorId(id: Int): Sala
    suspend fun buscarPorNombre(nombre: String): List<Sala>
    suspend fun buscarPorResponsable(responsable: String): List<Sala>
    suspend fun buscarPorFecha(fecha: LocalDateTime): List<Sala>
    suspend fun ingresarSala(usuario:Credenciales , sala: Sala)
    suspend fun borrarSala(id: Int)
    suspend fun modificarSala(id: Int, sala: Sala)


}