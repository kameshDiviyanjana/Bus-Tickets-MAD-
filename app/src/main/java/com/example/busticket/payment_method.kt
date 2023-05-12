package com.example.busticket

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class payment_method : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_method)

        val next = findViewById<Button>(R.id.paymentidbtn)

        next.setOnClickListener {

               val showpage = Intent(this,tickeDetails::class.java)
              startActivity(showpage)
        }
    }


}