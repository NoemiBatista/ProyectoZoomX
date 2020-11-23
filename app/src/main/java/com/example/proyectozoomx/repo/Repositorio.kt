package com.example.proyectozoomx.repo;
import com.example.proyectozoomx.entities.UrlApi;


interface Repositorio {
    fun save(urlApi: UrlApi)
    fun ConsultarBd(): String
}


