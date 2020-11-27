package com.example.proyectozoomx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectozoomx.entities.Credenciales
import com.example.proyectozoomx.entities.Rol
import com.example.proyectozoomx.entities.Usuario
import com.example.proyectozoomx.repositorio.RepositorioParametros
import kotlinx.android.synthetic.main.activity_config.*

class ConfigActivity : AppCompatActivity() {

    private lateinit var repositorioParametros: RepositorioParametros

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
            goZoomLoginActivity()
        }
    }
    fun goZoomLoginActivity() {
        NavegacionValues(usuario = Usuario("adm",
            Rol.ADMIN), credenciales = Credenciales("adm","adm"), this, ZoomLoginActivity::class.java).go()
    }
}