package com.example.busticket

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistewrUser : AppCompatActivity() {

    private lateinit var dbcon : DatabaseReference
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registewr_user)

        val rsgister =findViewById<Button>(R.id.resigster)
        val Aname = findViewById<EditText>(R.id.Aname)
        val disname =findViewById<EditText>(R.id.Adisname)
        val Aemail = findViewById<EditText>(R.id.Aemail)
        val passw= findViewById<EditText>(R.id.Apassword)
        val cancel=findViewById<Button>(R.id.cancel)
        val backSignUp=findViewById<TextView>(R.id.signIN)

        cancel.setOnClickListener{
            val cancel1 = Intent(this,MainActivity::class.java)
            startActivity(cancel1)
        }
        backSignUp.setOnClickListener{
            val back = Intent(this,MainActivity::class.java)
            startActivity(back)
        }

        rsgister.setOnClickListener {
            dbcon = FirebaseDatabase.getInstance().getReference("Users")
            val Username =Aname.text.toString()
            val Passwored =passw.text.toString()
            val email = Aemail.text.toString()
            val displayename = disname.text.toString()
            if(Username.isNotEmpty() && Passwored.isNotEmpty() && email.isNotEmpty() && email.isNotEmpty()){
                val adduser = userdata(Username,Passwored,email,displayename)
                dbcon.child(Username).setValue(adduser).addOnSuccessListener {

                    val move = Intent(this,MainActivity::class.java)
                    startActivity(move)
                }.addOnFailureListener {

                    Toast.makeText(this,"Sign up failed.Please try Again!", Toast.LENGTH_LONG).show()
                }
            }else{

                Toast.makeText(this,"Enter all the fields", Toast.LENGTH_LONG).show()
            }


        }
    }
}