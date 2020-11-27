package com.example.proyectozoomx.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectozoomx.entities.Credenciales
import com.example.proyectozoomx.entities.Usuario
import java.lang.Class

class NavegacionValues(
    val usuario: Usuario,
    val credenciales: Credenciales,
    val origen: AppCompatActivity,
    val destino: Class<out AppCompatActivity>
) {

    fun go() {
        val intent = Intent(origen, destino)
        val bundle = Bundle()
        bundle.putSerializable("usuario", usuario)
        bundle.putSerializable("credenciales", credenciales)
        intent.putExtras(intent)
        origen.startActivity(intent)
        origen.finish()


    }
}