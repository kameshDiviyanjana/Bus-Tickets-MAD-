package com.example.busticket

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class bashborde : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bashborde)

        val addbus = findViewById<ImageView>(R.id.addbus)
        val startTripe = findViewById<ImageView>(R.id.bushaltadd)
        val profile = findViewById<ImageView>(R.id.profile)
        val money = findViewById<ImageView>(R.id.money)

        addbus.setOnClickListener{

            val busadd = Intent(this,AddBusHalt::class.java)
            startActivity(busadd)
        }
        startTripe.setOnClickListener{

            val halt = Intent(this,displayeHalt::class.java)
            startActivity(halt)
        }
    }
}