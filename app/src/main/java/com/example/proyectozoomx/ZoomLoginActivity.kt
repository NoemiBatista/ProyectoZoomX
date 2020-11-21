package com.example.proyectozoomx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.proyectozoomx.usescases.ZoomApi
import kotlinx.android.synthetic.main.activity_zoom_login.*
import kotlinx.coroutines.launch


class ZoomLoginActivity : AppCompatActivity() {

    private lateinit var api: ZoomApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoom_login)
        //api = ClientZoomApi(Usuario("adm","adm"), "https://zoomx.freeddns.org:8443/")

        init()
    }

    private fun init(){
        send_login.setOnClickListener {
            lifecycleScope.launch{
                resultUsuario.text = api.send().toString()
                //TODO si login es exitoso tenemos que ir a Zoom_Menu_Principal
            }


        }
        cfgButton.setOnClickListener{
            // IR A CONFIGURACION
            //cuando vaya a la vista de base configuracion configuro bbdd
        }
    }
}
