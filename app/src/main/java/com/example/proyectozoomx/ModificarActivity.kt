package com.example.proyectozoomx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.proyectozoomx.entities.Sala
import kotlinx.android.synthetic.main.activity_ingreso__sala.*
import kotlinx.android.synthetic.main.activity_modificar.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class ModificarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar)

        init()
    }

    private fun init() {
        btn_guardarModificar.setOnClickListener {
            lifecycleScope.launch {

            }
        }
        btn_regresarModificar.setOnClickListener {
            //TODO VOLVER A MENU PRINICPAL
        }

        btn_BuscarPorId.setOnClickListener {
            lifecycleScope.launch {
                //TODO VER NOTAS EN EL PDF DE METODO DELETE
                // SI DA 200 LLENAS LOS DATOS QUE TRAE PARA MODIFICAR, Y SI DA 404 DAR ERROR

            }

        }


    }

    private fun guardarSalaModificarDeApi() {
        val sala = Sala(
            tvNombreModificar.text.toString(),
            tvResponsableModificar.text.toString(),
            LocalDateTime.parse(tvFehcaReservaModificar.text.toString()),
            tvTiempoReservaModificar.text.toString().toInt(),
            tvUrModificar.text.toString()
        )


    }
}