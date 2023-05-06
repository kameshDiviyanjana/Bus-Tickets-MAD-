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

class UserProfile : AppCompatActivity() {
    private lateinit var dbconnection : DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val name = findViewById<EditText>(R.id.name)
        val mobile = findViewById<EditText>(R.id.mobile)
        val uType= findViewById<EditText>(R.id.uType)
        val date = findViewById<EditText>(R.id.date)
        val district = findViewById<EditText>(R.id.district)
        val add = findViewById<Button>(R.id.add_Btn)
        val edit=findViewById<Button>(R.id.editBtn)

        edit.setOnClickListener {
            val edit1 = Intent(this, userUpdateDelete::class.java)
            startActivity(edit1)
        }


        add.setOnClickListener {

            val name1  = name.text.toString()
            val mobile1  = mobile.text.toString()
            val uType1  = uType.text.toString()
            val district1  = district.text.toString()
            val date1 = date.text.toString()
            if (name1.isNotEmpty() && mobile1.isNotEmpty() && uType1.isNotEmpty() && district1.isNotEmpty() &&date1.isNotEmpty() ) {
                dbconnection = FirebaseDatabase.getInstance().getReference("user profile")
                var adddata = userProfiledata(name1, mobile1, uType1, district1, date1)

                dbconnection.child(name1).setValue(adddata).addOnSuccessListener {
                    name.text.clear()
                    mobile.text.clear()
                    uType.text.clear()
                    district.text.clear()
                    date.text.clear()

                    Toast.makeText(this, "Successful ", Toast.LENGTH_LONG).show()
                    val go = Intent(this, UserProfile::class.java)
                    startActivity(go)


                }.addOnFailureListener {

                    Toast.makeText(this, "User Not added ", Toast.LENGTH_LONG).show()

                }
            }else{
                Toast.makeText(this, "Please complete details ", Toast.LENGTH_LONG).show()
            }


        }

    }
}