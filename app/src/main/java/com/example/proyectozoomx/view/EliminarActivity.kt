package com.example.proyectozoomx.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.proyectozoomx.R
import kotlinx.android.synthetic.main.activity_eliminar.*
import kotlinx.android.synthetic.main.activity_zoom__menu__principal.*
import kotlinx.coroutines.launch

class EliminarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar)

        init()
    }

    private fun init() {
        btn_EliminarPorId.setOnClickListener {
            lifecycleScope.launch {
                //TODO INVOCAR AL METODO DELETE DE LA API ( fun givenAdmin_whenDeleteSala_thenGetResponse204()  )
            }
        }
        btn_regresarEliminar.setOnClickListener {
            //TODO REGRESAR A MENU PRINCIPAL
        }
    }

}