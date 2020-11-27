package com.example.proyectozoomx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectozoomx.Persistence.baseZoom
import com.example.proyectozoomx.entities.*
import com.example.proyectozoomx.repositorio.ParametrosSQL
import com.example.proyectozoomx.repositorio.RepositorioParametros
import kotlinx.android.synthetic.main.activity_config.*

class ConfigActivity : AppCompatActivity() {

    private lateinit var repositorio: RepositorioParametros
    private lateinit var urlDeBase: UrlApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)
        repositorio = ParametrosSQL(baseZoom(this, "Parametros", null, 1))
        urlDeBase = repositorio.consultarBd()
        edUrl.setText(urlDeBase.url)
        edPuerto.setText(urlDeBase.url.toString())

        init()


    }

    private fun init() {
        btnGuardar.setOnClickListener {
            var parametros = Parametros(edUrl.text.toString(), edPuerto.text.toString().toInt())
            if (urlDeBase.puerto == 0 && urlDeBase.url == "3") {
                repositorio.save(parametros)
            }
            repositorio.update(parametros)

            Toast.makeText(this, "Envio Correcto", Toast.LENGTH_SHORT).show()
        }
        btnVolver.setOnClickListener {
            goZoomLoginActivity()
        }
    }

    fun goZoomLoginActivity() {

        startActivity(Intent(this, ZoomLoginActivity::class.java))
        finish()
//        NavegacionValues(
//            usuario = Usuario(
//                "adm",
//                Rol.ADMIN
//            ), credenciales = Credenciales("adm", "adm"), this, ZoomLoginActivity::class.java
//        ).go()
    }
}