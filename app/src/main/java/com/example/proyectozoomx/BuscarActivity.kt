package com.example.proyectozoomx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_buscar.*
import kotlinx.coroutines.launch

class BuscarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar)

        init()
    }

    private fun init() {
        btnBuscarSalaPorNombre.setOnClickListener{
            lifecycleScope.launch {
                //TODO  VER VIDEO  DEL 20/11
            }
        }
        btnBuscarPorFecha.setOnClickListener{
            lifecycleScope.launch {
                //TODO  VER VIDEO  DEL 20/11
            }
        }
        btnBuscarPorResponsable.setOnClickListener{
            lifecycleScope.launch {
                //TODO  VER VIDEO  DEL 20/11
            }
        }
        btnRegresarBuscar.setOnClickListener{
            // TODO regresar a MENU
        }

    }
}