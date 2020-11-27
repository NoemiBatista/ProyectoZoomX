package com.example.proyectozoomx.usescases

import com.example.proyectozoomx.entities.Credenciales
import com.example.proyectozoomx.entities.Identificacion
import com.example.proyectozoomx.entities.Sala
import com.example.proyectozoomx.entities.Usuario
import org.json.JSONObject
import java.time.LocalDateTime

interface ZoomApi {
    suspend fun send(credenciales: Credenciales): Usuario
    suspend fun buscarPorId(usuario: Credenciales,id: Int): Sala
    suspend fun buscarPorNombre(usuario: Credenciales,nombre: String): List<Sala>
    suspend fun buscarPorResponsable(usuario: Credenciales,responsable: String): List<Sala>
    suspend fun buscarPorFecha(usuario: Credenciales,fecha: LocalDateTime): List<Sala>
    suspend fun ingresarSala(usuario: Credenciales , sala: Sala)
    suspend fun borrarSala(usuario: Credenciales,id: Int)
    suspend fun modificarSala(usuario: Credenciales,id: Int, sala: Sala)


}