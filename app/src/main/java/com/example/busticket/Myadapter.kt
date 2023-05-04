package com.example.busticket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Myadapter(private val haltlist: ArrayList<BusHallt>) : RecyclerView.Adapter<Myadapter.MyviweHolder> (){

lateinit var  l : onItemClickListener
interface onItemClickListener{

    fun inItemckick(position: Int)
}

    fun setonItemClickListener(ls : onItemClickListener) {
        l = ls
    }
//var onItemclick : ((BusHallt)-> Unit) ?= null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviweHolder {
        val bushalt = LayoutInflater.from(parent.context).inflate(R.layout.itemas,parent,false)

        return MyviweHolder(bushalt,l)
    }


    override fun onBindViewHolder(holder: MyviweHolder, position: Int) {

        val currentitem = haltlist[position]
          holder.halltID.text =currentitem.halltID
          holder.halltname.text=currentitem.halltname
     /*  holder.itemView.setOnClickListener {
              onItemclick?.invoke(currentitem)
        }*/
    }

    override fun getItemCount(): Int {
        return haltlist.size

    }

    class MyviweHolder(Bushalt : View,listener: onItemClickListener) :RecyclerView.ViewHolder(Bushalt) {

                val halltID : TextView = Bushalt.findViewById(R.id.idhalt)
               val halltname : TextView = Bushalt.findViewById(R.id.haltname)

        init {
            /*Bushalt.setOnClickListener{v :View ->
                val position : Int =adapterPosition
                Toast.makeText(Bushalt.context,"you click work",Toast.LENGTH_LONG).show()*/

               Bushalt.setOnClickListener{

                   listener.inItemckick(adapterPosition)


            }
        }


    }
}