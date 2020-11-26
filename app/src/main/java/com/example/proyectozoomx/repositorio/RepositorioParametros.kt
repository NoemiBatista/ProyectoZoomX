package com.example.proyectozoomx.repo;
import com.example.proyectozoomx.entities.UrlApi;


interface RepositorioParametros {

    fun  save(urlApi: UrlApi)
    fun ConsultarBd() : UrlApi
    fun update(urlApi: UrlApi)

}

