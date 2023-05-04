package com.example.busticket

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class AddBusHalt : AppCompatActivity() {
    private lateinit var dbconnecte : DatabaseReference
    private lateinit var recycleBus : RecyclerView
    private lateinit var buslist : ArrayList<BusHallt>
    lateinit var  adapt : Myadapter
    lateinit var serch : ImageView
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_bus_halt)
        val HaltId = findViewById<EditText>(R.id.haltid)
        val Haltname = findViewById<EditText>(R.id.haliname)
        val registerHaltbtn = findViewById<Button>(R.id.haltbtn1235)
        val delete = findViewById<Button>(R.id.deletebtn)
        val k = findViewById<Button>(R.id.button2236)

        k.setOnClickListener {
            val p = Intent(this,displayeHalt::class.java)
            startActivity(p)
            updatehatl()
        }
        serch = findViewById(R.id.serchbt)
        registerHaltbtn.setOnClickListener {

            var halltID =HaltId.text.toString()
            var halltname = Haltname.text.toString()

            if(halltID.isEmpty() && halltname.isEmpty()){

                Toast.makeText(this,"Enter Details correct liye", Toast.LENGTH_LONG).show()
            }else{
                addBus(halltID,halltname)
            }
        }
        serch.setOnClickListener{
            val HaltId = findViewById<EditText>(R.id.haltid)
            val halt :String = HaltId.text.toString()
            if(halt.isNotEmpty()){
                findhalt(halt)
            }else{
                Toast.makeText(this,"Enter valide details", Toast.LENGTH_LONG).show()
            }

        }
        delete.setOnClickListener {
            val HaltId = findViewById<EditText>(R.id.haltid)
            val deiete : String = HaltId.text.toString()
            Haltdelete(deiete)
        }

        //recycle()
        recycleBus =findViewById(R.id.mtrecycle)
        recycleBus.layoutManager = LinearLayoutManager(this)
        recycleBus.setHasFixedSize(true)

        buslist = arrayListOf()
        adapt = Myadapter(buslist)
        displayeBushalt()
        /* adapt.onItemclick ={
                  val intent =Intent(this,BusTickeclculate::class.java)
                intent.putExtra("ticket",it)
             Toast.makeText(this,"work",Toast.LENGTH_LONG).show()
             startActivity(intent)
         }*/

    }

    private fun updatehatl() {
        val HaltId = findViewById<EditText>(R.id.haltid)
        val Haltname = findViewById<EditText>(R.id.haliname)
        var halltID =HaltId.text.toString()
        var halltname = Haltname.text.toString()
        dbconnecte = FirebaseDatabase.getInstance().getReference("BUSHalt")
        val updatehatls = mapOf<String,String>(

            "halltname" to halltname
        )
        dbconnecte.child(halltID).updateChildren(updatehatls).addOnSuccessListener {
            HaltId.text.clear()
            Haltname.text.clear()
        }.addOnFailureListener {
            Toast.makeText(this,"update   not catch ", Toast.LENGTH_LONG).show()
        }
    }


    private fun findhalt(halt: String) {
        val Haltname = findViewById<EditText>(R.id.haliname)

        dbconnecte = FirebaseDatabase.getInstance().getReference("BUSHalt")

        dbconnecte.child(halt).get().addOnSuccessListener {
            if(it.exists()){
                val bushalts = it.child("halltname").value
                Toast.makeText(this,"catch succefull", Toast.LENGTH_LONG).show()
                Haltname.setText(bushalts.toString())

            }else{
                Toast.makeText(this,"catch  not succefull", Toast.LENGTH_LONG).show()

            }
        }
    }
    private fun Haltdelete(deiete: String) {
        val HaltId = findViewById<EditText>(R.id.haltid)
        val Haltname = findViewById<EditText>(R.id.haliname)
        dbconnecte = FirebaseDatabase.getInstance().getReference("BUSHalt")
        dbconnecte.child(deiete).removeValue().addOnSuccessListener {
            Toast.makeText(this,"delete succefull", Toast.LENGTH_LONG).show()
            Haltname.text.clear()
            HaltId.text.clear()

        }.addOnFailureListener {
            Toast.makeText(this,"delete fail", Toast.LENGTH_LONG).show()
            Haltname.text.clear()
            HaltId.text.clear()
        }

    }


    fun addBus(halltID: String, halltname: String) {
        val HaltId = findViewById<EditText>(R.id.haltid)
        val Haltname = findViewById<EditText>(R.id.haliname)

        dbconnecte = FirebaseDatabase.getInstance().getReference("BUSHalt")
        var bushalt = BusHallt(halltID,halltname)
        dbconnecte.child(halltID).setValue(bushalt).addOnSuccessListener {

            Haltname.text.clear()
            HaltId.text.clear()
            Toast.makeText(this,"Succefull Add ", Toast.LENGTH_LONG).show()

        }.addOnFailureListener{
            Toast.makeText(this,"connection not works", Toast.LENGTH_LONG).show()
        }
    }
    private fun displayeBushalt() {
        dbconnecte = FirebaseDatabase.getInstance().getReference("BUSHalt")

        dbconnecte.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){

                    for (busSnapshor in snapshot.children){
                        val halt = busSnapshor.getValue(BusHallt::class.java)
                        buslist.add(halt!!)
                    }
                    var adp = Myadapter(buslist)
                    recycleBus.adapter =adp
                    adp.setonItemClickListener(object :Myadapter.onItemClickListener{
                        override fun inItemckick(position: Int) {
                            Toast.makeText(this@AddBusHalt,"you click item code works", Toast.LENGTH_LONG).show()
                            val intent = Intent(this@AddBusHalt,BusTickeclculate::class.java)
                            intent.putExtra("id",buslist[position].halltID)
                            intent.putExtra("name",buslist[position].halltname)
                            startActivity(intent)
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