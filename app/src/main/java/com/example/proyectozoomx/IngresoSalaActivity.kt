package com.example.proyectozoomx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.proyectozoomx.entities.Credenciales
import com.example.proyectozoomx.entities.Sala
import com.example.proyectozoomx.entities.Usuario
import com.example.proyectozoomx.usescases.ZoomApi
import kotlinx.android.synthetic.main.activity_ingreso__sala.*
import kotlinx.android.synthetic.main.activity_modificar.*
import kotlinx.android.synthetic.main.activity_zoom_login.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class IngresoSalaActivity : AppCompatActivity() {
    private lateinit var sala: Sala
    private lateinit var api: ZoomApi
    private lateinit var credenciales: Credenciales
    private lateinit var usuario: Usuario


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingreso__sala)
        val bundle: Bundle? = this.intent.extras
        credenciales = bundle!!.getSerializable("credenciales") as Credenciales
        usuario = bundle!!.getSerializable("usuario") as Usuario

        init()
    }


    private fun init() {
        btn_guardar.setOnClickListener {
            lifecycleScope.launch {
            edToSala()
                api.ingresarSala(credenciales,sala)



            }
        }
        btn_regresar.setOnClickListener {
            //TODO VOLVER A MENU PRINICPAL
        }


    }

    private fun edToSala() {
        val sala = Sala(
            tvResponsable.text.toString(),
            tvResponsableModificar.text.toString(),
            LocalDateTime.parse(tvFechaReserva.text.toString()),
            tvTiempoReserva.text.toString().toInt(),
            tvTextUrl.text.toString(),
        )


    }


}
