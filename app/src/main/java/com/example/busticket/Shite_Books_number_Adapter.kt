package com.example.busticket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Shite_Books_number_Adapter(private val haltlist: ArrayList<Bookingnumber>) : RecyclerView.Adapter<Shite_Books_number_Adapter.MyviweHolder>(){

    lateinit var  l : onItemClickListener
    interface onItemClickListener{

        fun inItemckick(position: Int)
    }

    fun setonItemClickListener(ls : onItemClickListener) {
        l = ls
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviweHolder {
        var bushalts = LayoutInflater.from(parent.context).inflate(R.layout.item_shite,parent,false)
        return MyviweHolder(bushalts,l)
    }

    override fun onBindViewHolder(holder: MyviweHolder, position: Int) {
        val currentitem = haltlist[position]
        holder.numbersh.text = currentitem.number
    }

    override fun getItemCount(): Int {
        return haltlist.size
    }
    class MyviweHolder(itemviewdispaly: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemviewdispaly){

   val numbersh : TextView = itemviewdispaly.findViewById(R.id.numberhalt)
        init {
            /*Bushalt.setOnClickListener{v :View ->
                val position : Int =adapterPosition
                Toast.makeText(Bushalt.context,"you click work",Toast.LENGTH_LONG).show()*/

            itemviewdispaly.setOnClickListener {

                listener.inItemckick(adapterPosition)


            }
        }
    }
}