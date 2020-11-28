package com.example.proyectozoomx.entities

data class UrlApi(val url: String, val puerto: Int) {

    public fun urlConcatenada(): String {

        return url + ":" + puerto

    }
}