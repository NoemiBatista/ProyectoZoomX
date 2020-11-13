package com.example.proyectozoomx.usescases

import com.example.proyectozoomx.entities.Identificacion
import com.example.proyectozoomx.entities.Usuario
import org.json.JSONObject

interface ZoomApi {
     fun send(): JSONObject

     suspend fun accesoLogin(usuario: Identificacion): Usuario
}