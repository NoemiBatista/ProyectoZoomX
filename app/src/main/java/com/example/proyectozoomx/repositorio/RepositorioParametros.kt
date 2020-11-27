package com.example.proyectozoomx.repositorio

import com.example.proyectozoomx.entities.Parametros

interface RepositorioParametros {

    open fun save(ParametrosSQL: Parametros) {}
}