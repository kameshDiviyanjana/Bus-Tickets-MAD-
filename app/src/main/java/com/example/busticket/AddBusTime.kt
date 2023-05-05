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

class AddBusTime : AppCompatActivity() {
    private lateinit var dbconection : DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_bus_time)
        val add = findViewById<Button>(R.id.Addtime)
        val wheres = findViewById<EditText>(R.id.whereredts)
        val Id = findViewById<EditText>(R.id.ideds)
        val to = findViewById<EditText>(R.id.toedts)
        val times = findViewById<EditText>(R.id.timetAddedt)

        add.setOnClickListener {

            val from = to.text.toString()
            val where = wheres.text.toString()
            val date =times.text.toString()
            val id = Id.text.toString()

            dbconection = FirebaseDatabase.getInstance().getReference("Calender")
            val addtime = timetable(id,from,where,date)

            dbconection.child(id).setValue(addtime).addOnSuccessListener {

                Id.text.clear()
                to.text.clear()
                times.text.clear()
                wheres.text.clear()
                Toast.makeText(this,"Add sucefull",Toast.LENGTH_LONG).show()
                val gonext = Intent(this,BusTimeTable::class.java)
                startActivity(gonext)


            }.addOnFailureListener {
                Toast.makeText(this,"DB connection fails",Toast.LENGTH_LONG).show()
            }

        }

    }



}