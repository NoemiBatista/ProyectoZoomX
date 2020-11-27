package com.example.proyectozoomx.repositorio

import android.database.sqlite.SQLiteOpenHelper
import com.example.proyectozoomx.entities.UrlApi
import com.example.proyectozoomx.repositorio.RepositorioParametros


data class ParametrosSQL(val dbHelper: SQLiteOpenHelper) : RepositorioParametros {

    private val datos: String = "ParametrosSentenciaSQL"

    override fun save(urlApi: UrlApi) {
        val db = dbHelper.writableDatabase
        val sql = "insert into UrlApi (id,url,puerto) values (1,${urlApi.url},'${urlApi.puerto}')"

        db.execSQL(sql)
        db.close()
    }

    override fun update(urlApi: UrlApi) {
        val db = dbHelper.writableDatabase
        val sql = "update  UrlApi set url= '${urlApi.url}' ,puerto='${urlApi.puerto}'"

        db.execSQL(sql)
        db.close()
    }

    override fun ConsultarBd(): UrlApi {
        val db = dbHelper.readableDatabase
        val sql = "select url, puerto from UrlApi"
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

