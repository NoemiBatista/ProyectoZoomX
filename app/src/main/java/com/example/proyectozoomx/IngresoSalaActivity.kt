package com.example.proyectozoomx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_ingreso__sala.*
import kotlinx.coroutines.launch

class IngresoSalaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingreso__sala)
        init()
    }

    private fun init() {
        btn_guardar.setOnClickListener {
            lifecycleScope.launch {
              //TODO INVOCAR AL METODO INGRESAR DE LA API ( fun givenAdmin_whenPostSala_thenGetResponse201() )
            }
        }
        btn_regresar.setOnClickListener{
            //TODO VOLVER A MENU PRINICPAL
        }


    }
}
