package com.example.proyectozoomx.repositorio

import android.database.sqlite.SQLiteOpenHelper
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.example.proyectozoomx.entities.Parametros


data class ParametrosSQL (val HelperSql: SQLiteOpenHelper): RepositorioParametros, Parcelable {

    private val datos: String = "ParametrosSentenciaSQL"

    override fun save(ParametrosSQL: Parametros) {

        Log.i(datos, "Se insertan Datos")
        val db = HelperSql.writableDatabase
        val sql = "insert into Parametros (url,puerto) values (${ParametrosSQL.url},'${ParametrosSQL.puerto}')"
        Log.i(datos, "Se Ejecutan Datos: $sql")
        db.execSQL(sql)
        db.close()


    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ParametrosSQL> {
        override fun createFromParcel(parcel: Parcel): ParametrosSQL {
            return ParametrosSQL(parcel)
        }

        override fun newArray(size: Int): Array<ParametrosSQL?> {
            return arrayOfNulls(size)
        }
    }
}
