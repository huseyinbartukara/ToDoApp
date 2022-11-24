package com.example.todoapp

import android.content.ContentValues

class Maddelerdao {

    fun tumMaddeler(vt:VeritabaniYardimcisi) : ArrayList<Maddeler>{

        val db = vt.writableDatabase
        val maddelerListe  = ArrayList<Maddeler>()
        val c = db.rawQuery("SELECT * FROM maddeler",null)

        while(c.moveToNext()){
            val madde = Maddeler(c.getInt(c.getColumnIndex("madde_id"))
                ,c.getString(c.getColumnIndex("madde_ad")))

            maddelerListe.add(madde)
        }
        return maddelerListe
    }


    fun maddeSil(vt:VeritabaniYardimcisi,madde_id:Int){
        val db = vt.writableDatabase
        db.delete("maddeler","madde_id=?", arrayOf(madde_id.toString()))
        db.close()
    }

    fun maddeEkle(vt:VeritabaniYardimcisi,madde_ad:String){
        val db = vt.writableDatabase
        val values = ContentValues()
        values.put("madde_ad",madde_ad)

        db.insertOrThrow("maddeler",null,values)
        db.close()
    }



}