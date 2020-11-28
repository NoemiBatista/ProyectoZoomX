package com.example.proyectozoomx

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.proyectozoomx.Persistence.baseZoom
import com.example.proyectozoomx.entities.Credenciales
import com.example.proyectozoomx.entities.Sala
import com.example.proyectozoomx.entities.Usuario
import com.example.proyectozoomx.repositorio.ParametrosSQL
import com.example.proyectozoomx.repositorio.RepositorioParametros
import com.example.proyectozoomx.usescases.ClientZoomApi
import com.example.proyectozoomx.usescases.ZoomApi
import kotlinx.android.synthetic.main.activity_buscar.*
import kotlinx.android.synthetic.main.activity_config.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class BuscarActivity : AppCompatActivity() {
    private lateinit var sala: Sala
    private lateinit var repositorio: RepositorioParametros
    private lateinit var api: ZoomApi
    private lateinit var credenciales: Credenciales
    private lateinit var usuario: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar)
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

        btnBuscarSalaPorNombre.setOnClickListener {

            lifecycleScope.launch {
                val nombre = edNombreSalaABuscar.text.toString()
                val salas = api.buscarPorNombre(
                    credenciales,
                    nombre
                )
                var adapter = SalaAdapter(this@BuscarActivity, salas);
                tvResultadoBusqueda.adapter = adapter

            }

        }
        btnBuscarPorFecha.setOnClickListener {
            lifecycleScope.launch {
                val fecha = LocalDateTime.parse(edFechaABuscar.text.toString())
                val salas = api.buscarPorFecha(
                    credenciales, fecha
                )
                var adapter = SalaAdapter(this@BuscarActivity, salas);
                tvResultadoBusqueda.adapter = adapter


            }
        }

        btnBuscarPorResponsable.setOnClickListener {
            lifecycleScope.launch {
                val responsable = edResponsableABuscar.text.toString()
                val salas = api.buscarPorResponsable(
                    credenciales,
                    responsable
                )
                var adapter = SalaAdapter(this@BuscarActivity, salas);
                tvResultadoBusqueda.adapter = adapter
            }
        }

        btnRegresarBuscar.setOnClickListener {
            val intent = Intent(this, ZoomMenuPrincipalActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("usuario", usuario)
            bundle.putSerializable("credenciales", credenciales)
            intent.putExtras(bundle)
            startActivity(intent)
            finish()
        }

    }

    class SalaAdapter(private val context: Context, private val arrayList: List<Sala>) :
        BaseAdapter() {
        private lateinit var tvvNombre: TextView
        private lateinit var tvvResponsable: TextView
        private lateinit var tvvFechaReserva: TextView
        private lateinit var tvvHoras: TextView

        override fun getCount(): Int {
            return arrayList.size
        }

        override fun getItem(position: Int): Any {
            return position
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
            var convertView = convertView
            convertView = LayoutInflater.from(context).inflate(
                R.layout.sala_item_view,
                parent,
                false
            )
            tvvNombre = convertView.findViewById(R.id.tvvNombre)
            tvvResponsable = convertView.findViewById(R.id.tvvResponsable)
            tvvFechaReserva = convertView.findViewById(R.id.tvvFechaReserva)
            tvvHoras = convertView.findViewById(R.id.tvvHoras)

            tvvNombre.text = arrayList[position].nombre
            tvvResponsable.text = arrayList[position].responsable
            tvvFechaReserva.text = arrayList[position].fechaDeReserva.toString()
            tvvHoras.text = arrayList[position].tiempoReservaEnHoras.toString()
            return convertView
        }
    }
}