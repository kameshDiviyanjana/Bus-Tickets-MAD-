package com.example.busticket

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class BusTimeTable : AppCompatActivity() {
    private lateinit var  dbconection :DatabaseReference
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_time_table)

        val updates = findViewById<Button>(R.id.updatesbtn)
        val deletes = findViewById<Button>(R.id.deletebtns)
        val search =  findViewById<ImageView>(R.id.timesearch)
        val idtime = findViewById<EditText>(R.id.idedt)
        val from =findViewById<EditText>(R.id.fromedt)
        val twhere =findViewById<EditText>(R.id.timewhre)
        val time =findViewById<EditText>(R.id.timeedt)

        search.setOnClickListener{


            dbconection = FirebaseDatabase.getInstance().getReference("Calender")
            val id  =idtime.text.toString()
            dbconection.child(id).get().addOnSuccessListener {

                if (it.exists()){

                    val did = it.child("id").value
                    val times = it.child("date").value
                    val froms =it.child("from").value
                    val wheres  =it.child("where").value

                    idtime.setText(did.toString())
                    from.setText(froms.toString())
                    twhere.setText(wheres.toString())
                    time.setText(times.toString())
                }
            }.addOnFailureListener {

                Toast.makeText(this,"fali to catch connectiong",Toast.LENGTH_SHORT).show()
            }

        }

        updates.setOnClickListener {

            dbconection = FirebaseDatabase.getInstance().getReference("Calender")
           val id=  idtime.text.toString()
           val timefrom =  from.text.toString()
           val timeWhere=  twhere.text.toString()
            val Timebus = time.text.toString()

            val updatetime = mapOf<String,String>(
                "id" to id,
                "date" to Timebus,
                "from" to timefrom,
                "where" to timeWhere
            )

            dbconection.child(id).updateChildren(updatetime).addOnSuccessListener {

                idtime.text.clear()
                from.text.clear()
                twhere.text.clear()
                time.text.clear()
                Toast.makeText(this,"sucessfull",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,"fali to update connectiong",Toast.LENGTH_SHORT).show()
            }

        }

        deletes.setOnClickListener {
            val id  =idtime.text.toString()
            dbconection = FirebaseDatabase.getInstance().getReference("Calender")

            dbconection.child(id).removeValue().addOnSuccessListener {

                idtime.text.clear()
                from.text.clear()
                twhere.text.clear()
                time.text.clear()

            }.addOnFailureListener {
                Toast.makeText(this,"fali to delete connectiong",Toast.LENGTH_SHORT).show()
            }
        }
    }
}