package com.example.proyectozoomx.Repositorio

import com.example.proyectozoomx.entities.Parametros


open interface Repositorio_Parametros {

    open fun save(ParametrosSQL: Parametros) {}
}