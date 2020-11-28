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
    private lateinit var credenciales: Credenciales
    private lateinit var usuario: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)
        val bundle: Bundle? = this.intent.extras
        credenciales = bundle!!.getSerializable("credenciales") as Credenciales
        usuario = bundle!!.getSerializable("usuario") as Usuario

        repositorio = ParametrosSQL(baseZoom(this, "Parametros", null, 1))
        urlDeBase = repositorio.consultarBd()
        edUrl.setText(urlDeBase.url)
        edPuerto.setText(urlDeBase.puerto.toString())

        init()


    }

    private fun init() {
        btnGuardar.setOnClickListener {
            var parametros = Parametros(edUrl.text.toString(), edPuerto.text.toString().toInt())
            if (urlDeBase.puerto == 0 && urlDeBase.url == "") {
                repositorio.save(parametros)
            } else {
                repositorio.update(parametros)


            }
            Toast.makeText(this, "Envio Correcto", Toast.LENGTH_SHORT).show()
        }
        btnVolver.setOnClickListener {
            goZoomLoginActivity()
        }
    }

    fun goZoomLoginActivity() {

        val intent = Intent(this, ZoomMenuPrincipalActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("usuario", usuario)
        bundle.putSerializable("credenciales", credenciales)
        intent.putExtras(bundle)
        startActivity(intent)
        finish()
    }
}