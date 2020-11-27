package com.example.proyectozoomx.entities


data class UrlApi(val url: String, val puerto: Int){

    fun urlYpuerto(): String {

        return url+":"+puerto

    }
}



