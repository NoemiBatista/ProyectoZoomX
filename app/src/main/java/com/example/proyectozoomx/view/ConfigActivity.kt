package com.example.proyectozoomx.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectozoomx.R
import kotlinx.android.synthetic.main.activity_config.*

class ConfigActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        init()


    }

    private fun init() {
        btnGuardar.setOnClickListener {
            //TODO persistir en la base de datos con SQLi
        }

        btnVolver.setOnClickListener {
            //TODO ir a MENU PRINCIPIAL o LOGIN
        }
    }
}