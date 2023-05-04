package com.example.busticket

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class BusTickeclculate : AppCompatActivity() {

    var totale  : Int = 0
    private lateinit var Fromed : EditText




    private lateinit var NUmberpassenger : EditText
 private  lateinit var  pay : Button
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_tickeclculate)


            val tx1 = findViewById<EditText>(R.id.from)
            val tx2 = findViewById<TextView>(R.id.hati25)

           pay = findViewById(R.id.button)
              Fromed = findViewById(R.id.from)
            val  TOed = findViewById<EditText>(R.id.toedite)
         val destent = findViewById<EditText>(R.id.km)
            NUmberpassenger = findViewById(R.id.nopassenger)
         val bundle : Bundle? = intent.extras

        val id = bundle!!.getString("id")
        val haltname = bundle.getString("name")

         tx2.text = id
        tx1.setText(haltname)


//                val totaleprice = 2.5 * km.toFloat()
        pay.setOnClickListener {
            val p =destent.text.toString()
            val To = TOed.text.toString()
            val from = Fromed.text.toString()
            val passengerNumber = NUmberpassenger.text.toString()
            val u = p
            val totaleprice = 2.5 * u.toFloat() * passengerNumber.toInt()
            val km = totaleprice.toString()
            val intent = Intent(this,QRticket::class.java)
            intent.putExtra("From",from)
            intent.putExtra("TOhatte",To)
            intent.putExtra("Totale",km)
           intent.putExtra("number",passengerNumber)
            startActivity(intent)
        }



    }
}


