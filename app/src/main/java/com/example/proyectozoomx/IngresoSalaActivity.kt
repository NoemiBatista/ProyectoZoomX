package com.example.proyectozoomx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.proyectozoomx.entities.Credenciales
import com.example.proyectozoomx.entities.Sala
import com.example.proyectozoomx.entities.Usuario
import com.example.proyectozoomx.repositorio.ParametrosSQL
import com.example.proyectozoomx.usescases.ClientZoomApi
import com.example.proyectozoomx.usescases.ZoomApi
import kotlinx.android.synthetic.main.activity_ingreso__sala.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class IngresoSalaActivity : AppCompatActivity() {
    private lateinit var sala: Sala
    private lateinit var api: ZoomApi
    private lateinit var repositorio: ParametrosSQL
    private lateinit var credenciales: Credenciales
    private lateinit var usuario: Usuario


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingreso__sala)
        val bundle: Bundle? = this.intent.extras
        credenciales = bundle!!.getSerializable("credenciales") as Credenciales
        usuario = bundle!!.getSerializable("usuario") as Usuario
        api = ClientZoomApi("https://zoomx.freeddns.org:8443")

        init()
    }


    private fun init() {
        btn_guardar.setOnClickListener {
            lifecycleScope.launch {
                val sala = edToSala();
                api.ingresarSala(credenciales, sala)

            }
        }
        btn_regresar.setOnClickListener {
            val intent = Intent(this, ZoomMenuPrincipalActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("usuario", usuario)
            bundle.putSerializable("credenciales", credenciales)
            intent.putExtras(bundle)
            startActivity(intent)
            finish()
        }


    }

    private fun edToSala(): Sala {
        return Sala(
            tvNombre.text.toString(),
            tvResponsable.text.toString(),
            LocalDateTime.parse(tvFechaReserva.text.toString()),
          //  LocalDateTime.parse("2030-12-03T10:15:30"),
            tvTiempoReserva.text.toString().toInt(),
            tvTextUrl.text.toString(),
        )
    }


}
