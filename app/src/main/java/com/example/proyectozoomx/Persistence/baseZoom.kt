package com.example.proyectozoomx.Persistence

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class baseZoom (
    context: Context, name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    private val sqlCreate = "create table UrlApi (id Integer primary key , url Varchar, puerto Integer)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(sqlCreate)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //
    }
}

