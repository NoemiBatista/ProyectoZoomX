package com.example.proyectozoomx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.proyectozoomx.Persistence.baseZoom
import com.example.proyectozoomx.entities.Credenciales
import com.example.proyectozoomx.repo.Repositorio
import com.example.proyectozoomx.repo.Sentencia
import com.example.proyectozoomx.usescases.ClientZoomApi
import com.example.proyectozoomx.usescases.ZoomApi
import kotlinx.android.synthetic.main.activity_zoom_login.*
import kotlinx.coroutines.launch


class ZoomLoginActivity : AppCompatActivity() {

    private lateinit var api: ZoomApi
    private lateinit var repositorio: Repositorio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_zoom_login)


        repositorio = Sentencia(baseZoom(this, "parametros", null, 1))
        api = ClientZoomApi(repositorio.ConsultarBd())

        init()
    }

    private fun init(){
        send_login.setOnClickListener {
            lifecycleScope.launch{
                val credenciales = Credenciales(resultUsuario.text.toString(),password.text.toString())
                resultUsuario.text = api.send(credenciales).toString()
                //TODO si login es exitoso tenemos que ir a Zoom_Menu_Principal

                //cfgButton.setOnClickListener{irConfig()}
                    // IR A CONFIGURACION
                    //cuando vaya a la vista de base configuracion configuro bbdd }
            }


        }

    }



  /*  private fun irConfig(){
        startActivities(Intent(this,ConfigActivity::class.java)
            finish()


    }*/

}
