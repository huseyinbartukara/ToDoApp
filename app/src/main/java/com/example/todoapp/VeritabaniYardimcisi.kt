package com.example.todoapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class VeritabaniYardimcisi(context: Context) : SQLiteOpenHelper(context,"maddeler.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE \"maddeler\" (\n" +
                "\t\"madde_id\"\tINTEGER,\n" +
                "\t\"madde_ad\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"madde_id\" AUTOINCREMENT)\n" +
                ");")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS maddeler")
        onCreate(db)
    }


}