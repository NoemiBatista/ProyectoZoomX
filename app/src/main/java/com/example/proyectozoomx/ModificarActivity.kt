package com.example.proyectozoomx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.proyectozoomx.entities.Credenciales
import com.example.proyectozoomx.entities.Sala
import com.example.proyectozoomx.entities.Usuario
import com.example.proyectozoomx.usescases.ClientZoomApi
import com.example.proyectozoomx.usescases.ZoomApi
import kotlinx.android.synthetic.main.activity_ingreso__sala.*
import kotlinx.android.synthetic.main.activity_modificar.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class ModificarActivity : AppCompatActivity() {

    private lateinit var credenciales: Credenciales
    private lateinit var usuario: Usuario
    private lateinit var api: ZoomApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar)
        val bundle: Bundle? = this.intent.extras
        credenciales = bundle!!.getSerializable("credenciales") as Credenciales
        usuario = bundle!!.getSerializable("usuario") as Usuario
        api = ClientZoomApi("https://zoomx.freeddns.org:8443")

        init()
    }

    private fun init() {
        btn_guardarModificar.setOnClickListener {

            lifecycleScope.launch {
                val sala = guardarSalaModificarDeApi()
                val idModicar = edIdABuscar.text.toString().toInt()
                api.modificarSala (credenciales,idModicar,sala)

            }
        }
        btn_regresarModificar.setOnClickListener {
            val intent = Intent(this, ZoomMenuPrincipalActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("usuario", usuario)
            bundle.putSerializable("credenciales", credenciales)
            intent.putExtras(bundle)
            startActivity(intent)
            finish()
        }

        btn_BuscarPorId.setOnClickListener {
            lifecycleScope.launch {
                //TODO VER NOTAS EN EL PDF DE METODO DELETE
                // SI DA 200 LLENAS LOS DATOS QUE TRAE PARA MODIFICAR, Y SI DA 404 DAR ERROR

            }

        }


    }

    private fun guardarSalaModificarDeApi(): Sala {
        return Sala(
            tvNombreModificar.text.toString(),
            tvResponsableModificar.text.toString(),
            LocalDateTime.parse(tvFehcaReservaModificar.text.toString()),
            tvTiempoReservaModificar.text.toString().toInt(),
            tvUrModificar.text.toString()
        )


    }
}