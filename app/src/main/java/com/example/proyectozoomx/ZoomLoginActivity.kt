package com.example.proyectozoomx

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.proyectozoomx.Persistence.baseZoom
import com.example.proyectozoomx.entities.Credenciales
import com.example.proyectozoomx.entities.Rol
import com.example.proyectozoomx.entities.Usuario
import com.example.proyectozoomx.repositorio.ParametrosSQL
import com.example.proyectozoomx.repositorio.RepositorioParametros
import com.example.proyectozoomx.usescases.ClientZoomApi
import com.example.proyectozoomx.usescases.ZoomApi
import kotlinx.android.synthetic.main.activity_zoom_login.*
import kotlinx.coroutines.launch


class ZoomLoginActivity : AppCompatActivity() {

    private lateinit var credenciales: Credenciales
    private lateinit var usuario: Usuario


    private lateinit var api: ZoomApi
    private lateinit var repositorio: RepositorioParametros

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoom_login)



        //api = ClientZoomApi(Usuario("adm","adm"), "https://zoomx.freeddns.org:8443/")

        repositorio = ParametrosSQL(baseZoom(this, "Parametros", null, 1))
       api = ClientZoomApi(usuario = Credenciales("adm","adm"),"https://zoomx.freeddns.org:8443/usuario")


        init()
    }

    private fun init() {
        send_login.setOnClickListener {
            lifecycleScope.launch {
                val credenciales =
                    Credenciales(edUsuario.text.toString(), edPassword.text.toString())
                val usuario = api.send(credenciales)
                goZoomMenuPrincipalActivity(usuario, credenciales)
            }


        }
        cfgButton.setOnClickListener {
            goConfigActivity()

        }
    }

    fun goZoomMenuPrincipalActivity(usuario: Usuario, credenciales: Credenciales) {
        NavegacionValues(usuario, credenciales, this, ZoomMenuPrincipalActivity::class.java).go()
    }

    fun goConfigActivity() {
        NavegacionValues(usuario = Usuario("adm",Rol.ADMIN), credenciales = Credenciales("adm","adm"), this, ConfigActivity::class.java).go()
    }

}


