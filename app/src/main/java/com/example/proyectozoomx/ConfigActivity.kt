package com.example.proyectozoomx

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectozoomx.Persistence.baseZoom
import com.example.proyectozoomx.entities.UrlApi
import com.example.proyectozoomx.repo.Repositorio
import com.example.proyectozoomx.repo.Sentencia
import kotlinx.android.synthetic.main.activity_config.*

class ConfigActivity : AppCompatActivity() {
    private lateinit var repositorio: Repositorio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)
        repositorio = Sentencia(baseZoom(this, "parametros", null, 1))

        init()


    }

    private fun init() {
        btnGuardar.setOnClickListener {

            btnGuardar.setOnClickListener {
                val urlApi = UrlApi(edUrl.text.toString(), edPuerto.text.toString().toInt())
                repositorio.save(urlApi)
                Toast.makeText(this, "Datos enviados correctamente",Toast.LENGTH_SHORT).show()

            }

            //TODO persistir en la base de datos con SQLi
        }

        btnVolver.setOnClickListener {
            //TODO ir a MENU PRINCIPIAL o LOGIN
        }
    }
}