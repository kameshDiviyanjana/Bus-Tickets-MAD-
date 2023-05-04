package com.example.busticket

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class displayeHalt : AppCompatActivity() {
    private lateinit var recycleBus : RecyclerView
    private lateinit var buslists : ArrayList<dispalyhalt>
    lateinit var  adapt : Displayeadapter
    private lateinit var dbconnecte : DatabaseReference
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_displaye_halt)

        recycleBus =findViewById(R.id.Allhatldiapalya)
        recycleBus.layoutManager = LinearLayoutManager(this)
        recycleBus.setHasFixedSize(true)

        buslists = arrayListOf()
        adapt = Displayeadapter(buslists)
       displayeBushalt()
    }

    private fun displayeBushalt() {
        dbconnecte = FirebaseDatabase.getInstance().getReference("BUSHalt")
        dbconnecte.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){

                    for (busSnapshor in snapshot.children){
                        val halts = busSnapshor.getValue(dispalyhalt::class.java)
                        buslists.add(halts!!)
                    }
                    var adp = Displayeadapter(buslists)
                    recycleBus.adapter =adp
                    adp.setonItemClickListener(object :Displayeadapter.onItemClickListener{
                        override fun inItemckick(position: Int) {
                            val intent = Intent(this@displayeHalt,BusTickeclculate::class.java)
                            intent.putExtra("id",buslists[position].halltID)
                            intent.putExtra("name",buslists[position].halltname)

                            startActivity(intent)
                            Toast.makeText(this@displayeHalt,"you click item nrwfgvhvhvh code works", Toast.LENGTH_LONG).show()

                        }

                    })
                    /*   adapt = Myadapter(buslist)
                       displayeBushalt()
                       adapt.onItemclick ={
                           val intent =Intent(this@MainActivity,BusTickeclculate::class.java)
                           intent.putExtra("ticket",it)

                           startActivity(intent)
                       }*/
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }




        })

    }
}