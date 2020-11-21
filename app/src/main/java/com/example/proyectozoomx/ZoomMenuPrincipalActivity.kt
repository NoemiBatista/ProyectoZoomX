package com.example.proyectozoomx

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_zoom__menu__principal.*


class ZoomMenuPrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoom__menu__principal)


        val intent1 = Intent(this@ZoomMenuPrincipalActivity, IngresoSalaActivity::class.java)
        startActivity(intent)

        init()


    }

    private fun init() {
        btn_registrar.setOnClickListener {
            //TODO ir a ACTIVITY REGISTRAR
        }
        btn_busqueda.setOnClickListener {
            //TODO  ir a ACTIVITY BUSQUEDA
        }
        btn_actualizar.setOnClickListener {
            //TODO ir a ACTIVITY ACTUALIZAR
        }
        btn_Eliminar.setOnClickListener {
            //TODO ir a ACTIVITY ELIMINAR
        }
        btn_config.setOnClickListener {
            //TODO ir a ACTIVITY Configuracion
        }
        btn_salir.setOnClickListener {
            finish()
        }
    }

}
