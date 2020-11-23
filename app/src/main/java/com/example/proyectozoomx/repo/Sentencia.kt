package com.example.proyectozoomx.repo


import android.database.sqlite.SQLiteOpenHelper;
import com.example.proyectozoomx.entities.UrlApi


class Sentencia (val dbHelper:SQLiteOpenHelper):Repositorio {



    override fun save(urlApi: UrlApi) {
        val db = dbHelper.writableDatabase
        val sql = "insert into UrlApi (url,puerto) values (${urlApi.url},'${urlApi.puerto}')"

        db.execSQL(sql)
        db.close()
    }
    override fun ConsultarBd() : String{
        val db = dbHelper.readableDatabase
        val sql = "select url, puerto from UrlApi"
        db.rawQuery(sql,null)
        val cursor = db.rawQuery(sql,null)
        //chequeo de datos en base
        var combo =""
        if (cursor.moveToFirst()){
            val url = cursor.getString(0)
            val puerto = cursor.getString(1)
                combo = url + ":" + puerto
            }
                return combo


    }
}
