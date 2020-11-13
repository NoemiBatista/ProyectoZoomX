package com.example.proyectozoomx

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class Zoom_Menu_Principal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoom__menu__principal)


        val intent1 = Intent(this@Zoom_Menu_Principal, Ingreso_Sala::class.java)
        startActivity(intent)




    }
}
