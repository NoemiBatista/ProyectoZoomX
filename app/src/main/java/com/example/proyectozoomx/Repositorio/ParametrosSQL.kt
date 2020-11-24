package com.example.proyectozoomx.Repositorio

import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.proyectozoomx.entities.Parametros


data class ParametrosSQL (val HelperSql: SQLiteOpenHelper): Repositorio_Parametros() {

    private val datos: String = "ParamtrosSentenciaSQL"

    override fun save(ParametrosSQL: Parametros) {

        Log.i(datos, "Se insertan Datos")
        val db = HelperSql.writableDatabase
        val sql = "insert into Parametros (url,puerto) values (${ParametrosSQL.url},'${ParametrosSQL.puerto}')"
        Log.i(datos, "Se Ejecutan Datos: $sql")
        db.execSQL(sql)
        db.close()


    }
}
