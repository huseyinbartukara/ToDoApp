package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var vt:VeritabaniYardimcisi
    private lateinit var maddelerListe:ArrayList<Maddeler>
    private lateinit var adapter: MaddelerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarMain.title = "To-Do App"
        setSupportActionBar(toolbarMain)

        rv.setHasFixedSize(true)

        rv.layoutManager = LinearLayoutManager(this)

        vt = VeritabaniYardimcisi(this)

        tumMaddeleriAl()








        fab.setOnClickListener {

            val tasarim = layoutInflater.inflate(R.layout.alert_dialog_tasarim,null)

            val editTextMaddeEkle = tasarim.findViewById(R.id.editTextMaddeEkle) as EditText

            val ad = AlertDialog.Builder(this@MainActivity)

            ad.setTitle("MADDE EKLE")
            ad.setMessage("Yapmanız Gereken Maddeyi Girin")
            ad.setIcon(R.drawable.resim_note)

            ad.setView(tasarim)



            ad.setPositiveButton("kaydet"){dialogInterface, i->

                val madde = editTextMaddeEkle.text.toString().trim()

                if(TextUtils.isEmpty(madde)){
                    Snackbar.make(toolbarMain,"Madde Giriniz", Snackbar.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                Maddelerdao().maddeEkle(vt,madde)
                tumMaddeleriAl()

                Toast.makeText(this@MainActivity,"Madde Eklendi",Toast.LENGTH_SHORT).show()




            }

            ad.setNegativeButton("İptal"){dialogInterface, i->
                Toast.makeText(this@MainActivity,"İptal Tıklanıldı", Toast.LENGTH_SHORT).show()
            }

            ad.create().show()


        }

    }


    fun tumMaddeleriAl(){
        maddelerListe = Maddelerdao().tumMaddeler(vt)
        adapter = MaddelerAdapter(this@MainActivity,maddelerListe,vt)
        rv.adapter = adapter
    }

}