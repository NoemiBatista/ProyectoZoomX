package com.example.proyectozoomx.repositorio

import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.proyectozoomx.entities.Parametros
import com.example.proyectozoomx.entities.UrlApi
import com.example.proyectozoomx.repositorio.RepositorioParametros


data class ParametrosSQL(val dbHelper: SQLiteOpenHelper) : RepositorioParametros {

    private val datos: String = "ParametrosSentenciaSQL"

    override fun save(parametros: Parametros) {

        val db = dbHelper.writableDatabase
        val sql = "insert into Parametros (id,url,puerto) values(1,  '${parametros.url}','${parametros.puerto}')"

        db.execSQL(sql)
        db.close()
    }

    override fun update(parametros: Parametros) {
        val db = dbHelper.writableDatabase
        val sql = "update Parametros set url='${parametros.url}', puerto='${parametros.puerto}'"

        db.execSQL(sql)
        db.close()
    }

    override fun consultarBd(): UrlApi {
        val db = dbHelper.readableDatabase
        val sql = "select url,puerto from Parametros where id=1"
        db.rawQuery(sql, null)
        val cursor = db.rawQuery(sql, null)
        //chequeo de datos en base
        var url = ""
        var puerto = 0
        if (cursor.moveToFirst()) {
            url = cursor.getString(0)
            puerto = cursor.getInt(1)

        }
        return UrlApi(url, puerto)

    }
}

