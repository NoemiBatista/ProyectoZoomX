package com.example.proyectozoomx

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE

import androidx.appcompat.app.AppCompatActivity
import com.example.proyectozoomx.entities.Credenciales
import com.example.proyectozoomx.entities.Rol
import com.example.proyectozoomx.entities.Usuario
import kotlinx.android.synthetic.main.activity_zoom__menu__principal.*


class ZoomMenuPrincipalActivity : AppCompatActivity() {

    private lateinit var credenciales: Credenciales
    private lateinit var usuario: Usuario


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoom__menu__principal)

        val bundle = this.intent.extras
        credenciales = bundle!!.getSerializable("credenciales") as Credenciales
        usuario = bundle!!.getSerializable("usuario") as Usuario

        tvUsuarioLogueado.text = usuario.rol.name
        if (usuario.rol == Rol.USER) {
            btn_registrar.visibility = GONE
            btn_actualizar.visibility = GONE
            btn_Eliminar.visibility = GONE
        }




        init()


    }

    private fun goIngresoSalaActivity() {
        //    NavegacionValues(usuario, credenciales, this, IngresoSalaActivity::class.java).go()
        val intent = Intent(this, IngresoSalaActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("usuario", usuario)
        bundle.putSerializable("credenciales", credenciales)
        intent.putExtras(bundle)
        startActivity(intent)
        finish()
    }

    private fun goModificarActivity() {
//        NavegacionValues(usuario, credenciales, this, ModificarActivity::class.java).go()
        val intent = Intent(this, ModificarActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("usuario", usuario)
        bundle.putSerializable("credenciales", credenciales)
        intent.putExtras(bundle)
        startActivity(intent)
        finish()
    }

    private fun goBuscarActivity() {
        //    NavegacionValues(usuario, credenciales, this, BuscarActivity::class.java).go()
        val intent = Intent(this, BuscarActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("usuario", usuario)
        bundle.putSerializable("credenciales", credenciales)
        intent.putExtras(bundle)
        startActivity(intent)
        finish()
    }

    private fun goEliminarActivity() {
//        NavegacionValues(usuario, credenciales, this, EliminarActivity::class.java).go()
        val intent = Intent(this, EliminarActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("usuario", usuario)
        bundle.putSerializable("credenciales", credenciales)
        intent.putExtras(bundle)
        startActivity(intent)
        finish()
    }

    private fun goConfigActivity() {
//        NavegacionValues(usuario, credenciales, this, ConfigActivity::class.java).go()
        val intent = Intent(this, ConfigActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("usuario", usuario)
        bundle.putSerializable("credenciales", credenciales)
        intent.putExtras(bundle)
        startActivity(intent)
        finish()
    }


    private fun init() {
        btn_registrar.setOnClickListener {
            goIngresoSalaActivity()
        }
        btn_busqueda.setOnClickListener {
            goBuscarActivity()
        }
        btn_actualizar.setOnClickListener {
            goModificarActivity()
        }
        btn_Eliminar.setOnClickListener {
            goEliminarActivity()
        }
        btn_config.setOnClickListener {
            goConfigActivity()
        }
        btn_salir.setOnClickListener {
            finish()
        }
    }

}
