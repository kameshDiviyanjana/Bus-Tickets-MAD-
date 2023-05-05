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

class bookinupdateDelete : AppCompatActivity() {
    private lateinit var dbconnection : DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookinupdate_delete)

        val delete = findViewById<Button>(R.id.deletebtns)
        val update =findViewById<Button>(R.id.updatebtn)
        val ids = findViewById<EditText>(R.id.ids)
        val From = findViewById<EditText>(R.id.fromss)
        val To = findViewById<EditText>(R.id.to)
        val Passenger = findViewById<EditText>(R.id.passengerid4)
        val dates = findViewById<EditText>(R.id.wheresedts12)
        val search =findViewById<ImageView>(R.id.searchicon)

        search.setOnClickListener{
            dbconnection = FirebaseDatabase.getInstance().getReference("Bookings")
            val id = ids.text.toString()
            dbconnection.child(id).get().addOnSuccessListener {
                if(it.exists()){
                  val date = it.child("date").value
                    val from =it.child("from").value
                    val passenge =it.child("passenge").value
                    val to =it.child("to").value
                    From.setText(from.toString())
                    To.setText(to.toString())
                    Passenger.setText(passenge.toString())
                    dates.setText(date.toString())


                }
            }.addOnFailureListener {
                Toast.makeText(this,"catch  not succefull bus", Toast.LENGTH_LONG).show()
            }
        }

        delete.setOnClickListener {

            val id  = ids.text.toString()

            dbconnection = FirebaseDatabase.getInstance().getReference("Bookings")
            dbconnection.child(id).removeValue().addOnSuccessListener {

                ids.text.clear()
                From.text.clear()
                To.text.clear()
                dates.text.clear()
                Passenger.text.clear()
            }.addOnFailureListener {
                Toast.makeText(this,"failes delete", Toast.LENGTH_LONG).show()
            }
        }
        update.setOnClickListener {
            dbconnection = FirebaseDatabase.getInstance().getReference("Bookings")
            val id  = ids.text.toString()
            val tos =  To.text.toString()
            val Froms= From.text.toString()
            val Date = dates.text.toString()
            val passengers = Passenger.text.toString()

            val updataedata = mapOf<String,String>(
                "date" to Date,
                "passenge" to passengers,
                "from" to Froms,
                "to" to  tos
            )

            dbconnection.child(id).updateChildren(updataedata).addOnSuccessListener {

                ids.text.clear()
                From.text.clear()
                To.text.clear()
                dates.text.clear()
                Passenger.text.clear()
            }.addOnFailureListener {
                Toast.makeText(this,"update not connectred", Toast.LENGTH_LONG).show()
            }
        }

    }
}