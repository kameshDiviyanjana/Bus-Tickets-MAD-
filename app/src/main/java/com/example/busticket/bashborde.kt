package com.example.busticket

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class bashborde : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bashborde)

        val addbus = findViewById<ImageView>(R.id.addbus)
        val startTripe = findViewById<ImageView>(R.id.bushaltadd)
        val profile = findViewById<ImageView>(R.id.profile)
        val money = findViewById<ImageView>(R.id.money)
        val disnamr = findViewById<TextView>(R.id.nickdash)
        val userprofile=findViewById<ImageView>(R.id.imageView3)
         val buble : Bundle?= intent.extras

         var name  = buble!!.getString("nickname")
        disnamr.text = name

        addbus.setOnClickListener{

            val busadd = Intent(this,AddBusHalt::class.java)
            startActivity(busadd)
        }
        userprofile.setOnClickListener{

            val userPro = Intent(this,UserProfile::class.java)
            startActivity(userPro)
        }


        startTripe.setOnClickListener{

            val halt = Intent(this,displayeHalt::class.java)
            startActivity(halt)
        }
        profile.setOnClickListener{

            val halts = Intent(this,ShitheBooking::class.java)
            startActivity(halts)
        }
        money.setOnClickListener{
            val timeTable = Intent(this,AddBusTime::class.java)
            startActivity(timeTable)
        }
    }
}