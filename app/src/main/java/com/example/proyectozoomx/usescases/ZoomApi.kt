package com.example.proyectozoomx.usescases

import com.example.proyectozoomx.entities.Credenciales
import com.example.proyectozoomx.entities.Sala
import com.example.proyectozoomx.entities.Usuario

interface ZoomApi {
    suspend fun send(credenciales: Credenciales): Usuario
    suspend fun buscarPorId(id: Int): Sala
}