package com.example.busticket

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ShitheBooking : AppCompatActivity() {
    private lateinit var dbconnection : DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shithe_booking)

        val ids = findViewById<EditText>(R.id.Toid)
        val From = findViewById<EditText>(R.id.fromid)
        val To = findViewById<EditText>(R.id.dateid)
        val Passenger = findViewById<EditText>(R.id.fromss)
        val dates = findViewById<EditText>(R.id.timeid)
        val submit = findViewById<Button>(R.id.submitrbtn)


        submit.setOnClickListener {

            val id  = ids.text.toString()
            val from  = From.text.toString()
            val to  = To.text.toString()
            val date  = dates.text.toString()
            val passenge  = Passenger.text.toString()

        dbconnection = FirebaseDatabase.getInstance().getReference("Bookings")
            var adddata= bookingdata(id,from,to,date,passenge)

             dbconnection.child(id).setValue(adddata).addOnSuccessListener {
                 ids.text.clear()
                 From.text.clear()
                 To.text.clear()
                 dates.text.clear()
                 Passenger.text.clear()

                 Toast.makeText(this,"sucessfull ",Toast.LENGTH_LONG).show()
                  val go = Intent(this,seat_select::class.java)
                 startActivity(go)



             }.addOnFailureListener {

                 Toast.makeText(this,"failes ",Toast.LENGTH_LONG).show()

             }


        }

    }
}