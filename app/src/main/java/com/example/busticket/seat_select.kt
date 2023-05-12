package com.example.busticket

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class seat_select : AppCompatActivity() {
    private lateinit var recycleBus : RecyclerView
    private lateinit var buslistss : ArrayList<Bookingnumber>
    lateinit var  adapt : Shite_Books_number_Adapter
    private lateinit var dbconnecte : DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_select)

        val nextupdate = findViewById<Button>(R.id.shiteBookId)
        nextupdate.setOnClickListener {
            val inten = Intent(this,payment_method::class.java)
            startActivity(inten)
        }
        recycleBus =findViewById(R.id.shitenumber)
        recycleBus.layoutManager = LinearLayoutManager(this)
        recycleBus.setHasFixedSize(true)

        buslistss = arrayListOf()
        adapt = Shite_Books_number_Adapter(buslistss)
        Shite_number()
    }

    private fun Shite_number() {
        dbconnecte = FirebaseDatabase.getInstance().getReference("ShiteNumber")
        dbconnecte.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (busSnapshor in snapshot.children){
                        val halts = busSnapshor.getValue(Bookingnumber::class.java)
                        buslistss.add(halts!!)
                    }
                    var adp =  Shite_Books_number_Adapter(buslistss)
                    recycleBus.adapter =adp
                    adp.setonItemClickListener(object : Shite_Books_number_Adapter.onItemClickListener{
                        override fun inItemckick(position: Int) {

                           val o = buslistss[position].number
                            val g = findViewById<EditText>(R.id.shownumber)
                           val d = g.setText(o)
                            Toast.makeText(this@seat_select,"Adding pass", Toast.LENGTH_LONG).show()

                        }

                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}