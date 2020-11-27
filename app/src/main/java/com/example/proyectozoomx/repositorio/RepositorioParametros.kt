package com.example.proyectozoomx.repositorio;

import com.example.proyectozoomx.entities.Parametros
import com.example.proyectozoomx.entities.UrlApi;


interface RepositorioParametros {

    fun save(parametros: Parametros )
    fun consultarBd(): UrlApi
    fun update(parametros: Parametros)

}

