package com.example.proyectozoomx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_ingreso__sala.*
import kotlinx.android.synthetic.main.activity_modificar.*
import kotlinx.coroutines.launch

class ModificarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar)

        init()
    }

    private fun init() {
        btn_guardarModificar.setOnClickListener {
            lifecycleScope.launch {
                //TODO INVOCAR AL METODO PATCH DE LA API ( fun givenAdmin_whenPatchSala_thenGetResponse204() )
            }
        }
        btn_regresarModificar.setOnClickListener{
            //TODO VOLVER A MENU PRINICPAL
        }

        btn_BuscarPorId.setOnClickListener{
            lifecycleScope.launch {
            //TODO VER NOTAS EN EL PDF DE METODO DELETE
                // SI DA 200 LLENAS LOS DATOS QUE TRAE PARA MODIFICAR, Y SI DA 404 DAR ERROR

            }

        }


    }
}