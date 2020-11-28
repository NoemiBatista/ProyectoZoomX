package com.example.proyectozoomx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.proyectozoomx.Persistence.baseZoom
import com.example.proyectozoomx.entities.Credenciales
import com.example.proyectozoomx.entities.Usuario
import com.example.proyectozoomx.repositorio.ParametrosSQL
import com.example.proyectozoomx.repositorio.RepositorioParametros
import com.example.proyectozoomx.usescases.ClientZoomApi
import com.example.proyectozoomx.usescases.ZoomApi
import kotlinx.android.synthetic.main.activity_buscar.*
import kotlinx.android.synthetic.main.activity_eliminar.*
import kotlinx.android.synthetic.main.activity_zoom__menu__principal.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class EliminarActivity : AppCompatActivity() {
    private lateinit var credenciales: Credenciales
    private lateinit var usuario: Usuario
    private lateinit var api: ZoomApi
    private lateinit var repositorio: RepositorioParametros

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar)
        val bundle: Bundle? = this.intent.extras
        credenciales = bundle!!.getSerializable("credenciales") as Credenciales
        usuario = bundle!!.getSerializable("usuario") as Usuario

        repositorio = ParametrosSQL(baseZoom(this, "Parametros", null, 1))
        val parametros = repositorio.consultarBd()
        val url = parametros.urlConcatenada()
        api = ClientZoomApi(url)

        init()
    }

    private fun init() {
        btn_EliminarPorId.setOnClickListener {
            lifecycleScope.launch {
                val idBorrar = edIdAEliminar.text.toString().toInt()
                api.borrarSala(
                    credenciales, idBorrar
                )

            }
        }

        btn_regresarEliminar.setOnClickListener {
            val intent = Intent(this, ZoomMenuPrincipalActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("usuario", usuario)
            bundle.putSerializable("credenciales", credenciales)
            intent.putExtras(bundle)
            startActivity(intent)
            finish()
        }
    }

}