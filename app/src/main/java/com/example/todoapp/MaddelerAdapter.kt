package com.example.todoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MaddelerAdapter(private val mContext:Context, private var maddelerListe:List<Maddeler>, private val vt:VeritabaniYardimcisi) : RecyclerView.Adapter<MaddelerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim: View) : RecyclerView.ViewHolder(tasarim){
        var textViewMadde :TextView
        var imageViewTrash : ImageView

        init {
            textViewMadde = tasarim.findViewById(R.id.textViewMadde)
            imageViewTrash = tasarim.findViewById(R.id.imageViewTrash)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.card_tasarim,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {

        val madde = maddelerListe.get(position)

        holder.textViewMadde.text = madde.madde_ad
        holder.imageViewTrash.setOnClickListener {

            Snackbar.make(holder.imageViewTrash,"Bu Madde Silinsin mi?", Snackbar.LENGTH_SHORT)
                .setAction("EVET"){
                    Maddelerdao().maddeSil(vt,madde.madde_id)
                    maddelerListe = Maddelerdao().tumMaddeler(vt)
                    notifyDataSetChanged()
                }.show()



        }


    }

    override fun getItemCount(): Int {
        return maddelerListe.size
    }


}