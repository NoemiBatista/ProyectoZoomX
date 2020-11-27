package com.example.proyectozoomx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.proyectozoomx.entities.Credenciales
import com.example.proyectozoomx.entities.Sala
import com.example.proyectozoomx.entities.Usuario
import com.example.proyectozoomx.usescases.ZoomApi
import kotlinx.android.synthetic.main.activity_buscar.*
import kotlinx.coroutines.launch

class BuscarActivity : AppCompatActivity() {

    private lateinit var api: ZoomApi
    private lateinit var credenciales: Credenciales
    private lateinit var usuario: Usuario
    private lateinit var sala: Sala

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar)

        val bundle: Bundle? = this.intent.extras
        credenciales = bundle!!.getSerializable("credenciales") as Credenciales
        usuario = bundle!!.getSerializable("usuario") as Usuario
        btnVolverBuscar.setOnClickListener { volverMenuPrincipal() }
        btnBuscarSalaPorNombre.setOnClickListener { buscarSalaNombre() }

    }

    private fun volverMenuPrincipal() {

        // Valores(usuario,credenciales, this,Zoom_Menu_Principal::class.java).ir()
    }

    private fun buscarSalaNombre() {

        val nombre: String = buscarSalaNombre.text.toString()
        lifecycleScope.launch {

            val salas: List<Sala> = api.buscarSala(credenciales, nombre)
            Log.d("Busq Salas", salas.toString())

        }
    }
}