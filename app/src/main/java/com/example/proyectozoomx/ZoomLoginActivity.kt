package com.example.proyectozoomx

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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


    private lateinit var api: ZoomApi
    private lateinit var repositorio: RepositorioParametros

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoom_login)


        repositorio = ParametrosSQL(baseZoom(this, "Parametros", null, 1))
        api = ClientZoomApi("https://zoomx.freeddns.org:8443/usuario/")


        init()
    }

    private fun init() {

        send_login.setOnClickListener {
            lifecycleScope.launch {
                val credenciales =
                    Credenciales(edUsuario.text.toString(), edPassword.text.toString())
                val usuario = api.send(credenciales)
                validator(usuario,credenciales)



            }


        }
        cfgButton.setOnClickListener {
            goConfigActivity()
        }


    }


    fun validator(usuario: Usuario,credenciales: Credenciales) {
        if (usuario.rol != Rol.INVALID) {

            goZoomMenuPrincipalActivity(usuario,credenciales)
        } else {
            Toast.makeText(this, "LOGIN INOCRRECTO", Toast.LENGTH_SHORT).show()
        }
    }

    fun goZoomMenuPrincipalActivity(usuario: Usuario,credenciales: Credenciales) {
       val intent=Intent(this, ZoomMenuPrincipalActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("usuario", usuario)
        bundle.putSerializable("credenciales", credenciales)
        intent.putExtras(bundle)
        startActivity(intent)
        finish()

//        NavegacionValues(
//            usuario = Usuario("adm", Rol.ADMIN),
//            credenciales = Credenciales("adm", "adm"),
//            this, ZoomMenuPrincipalActivity::class.java
//        ).go()
    }

    fun goConfigActivity() {

        startActivity(Intent(this, ConfigActivity::class.java))
        this.finish()
//        NavegacionValues(usuario = Usuario("adm",
//            Rol.ADMIN), credenciales = Credenciales("adm","adm"), this, ConfigActivity::class.java).go()
    }
}




