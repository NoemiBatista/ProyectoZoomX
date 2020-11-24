package com.example.proyectozoomx.usescases

import com.example.proyectozoomx.entities.Credenciales
import com.example.proyectozoomx.entities.Sala
import com.example.proyectozoomx.entities.Usuario

interface ZoomApi {
    suspend fun send(): Usuario
    suspend fun buscarPorId(id: Int): Sala
    suspend fun buscarSala(usuario: Credenciales, nombre: String): List<Sala>
}