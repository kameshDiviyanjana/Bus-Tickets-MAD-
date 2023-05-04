package com.example.busticket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Displayeadapter(private val haltlist: ArrayList<dispalyhalt>) : RecyclerView.Adapter<Displayeadapter.MyviweHolder> () {


    lateinit var  l : onItemClickListener
    interface onItemClickListener{

        fun inItemckick(position: Int)
    }

    fun setonItemClickListener(ls : onItemClickListener) {
        l = ls
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviweHolder {
        val bushalt = LayoutInflater.from(parent.context).inflate(R.layout.dispalye_halt,parent,false)
        return MyviweHolder(bushalt,l)
    }

    override fun onBindViewHolder(holder: MyviweHolder, position: Int) {
        val currentitem = haltlist[position]
        holder.halltname.text =currentitem.halltname

    }

    override fun getItemCount(): Int {
        return haltlist.size
    }

    class MyviweHolder(itemviewdispaly: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemviewdispaly){

        val halltname : TextView = itemviewdispaly.findViewById(R.id.haltnew)
        init {
            /*Bushalt.setOnClickListener{v :View ->
                val position : Int =adapterPosition
                Toast.makeText(Bushalt.context,"you click work",Toast.LENGTH_LONG).show()*/

            itemviewdispaly.setOnClickListener{

                listener.inItemckick(adapterPosition)


            }
        }
    }
}