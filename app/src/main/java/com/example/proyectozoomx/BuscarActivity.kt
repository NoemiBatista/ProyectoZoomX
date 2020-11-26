package com.example.proyectozoomx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.example.proyectozoomx.entities.Sala
import com.example.proyectozoomx.usescases.ZoomApi
import kotlinx.android.synthetic.main.activity_buscar.*
import kotlinx.coroutines.launch

class BuscarActivity : AppCompatActivity() {
    private lateinit var api: ZoomApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar)

        init()
    }

    private fun init() {

        btnBuscarSalaPorNombre.setOnClickListener{
            lifecycleScope.launch {

                val salas : List<Sala> = api.buscarPorNombre("sala") // TODO en nombre va el recibido desde el tex
                Log.d("salas",salas.toString())
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