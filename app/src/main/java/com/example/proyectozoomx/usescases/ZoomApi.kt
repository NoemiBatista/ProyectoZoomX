package com.example.proyectozoomx.usescases

import com.example.proyectozoomx.entities.Identificacion
import com.example.proyectozoomx.entities.Sala
import com.example.proyectozoomx.entities.Usuario
import org.json.JSONObject
import java.time.LocalDateTime

interface ZoomApi {
    suspend fun send(): Usuario
    suspend fun buscarPorId(id: Int): Sala
    suspend fun buscarPorNombre(nombre: String) : List<Sala>


}