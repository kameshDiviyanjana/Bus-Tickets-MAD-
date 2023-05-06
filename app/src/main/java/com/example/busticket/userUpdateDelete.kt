package com.example.busticket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class userUpdateDelete : AppCompatActivity() {
    private lateinit var dbconnection : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_update_delete)

        val delete = findViewById<Button>(R.id.deletebtns)
        val update =findViewById<Button>(R.id.updatebtn)
        val nameUpdate = findViewById<EditText>(R.id.nameUp)
        val nameUpdate1=findViewById<EditText>(R.id.nameUp3)
        val nameUpdate2=findViewById<TextView>(R.id.nameTxt)
        val mobileUpdate = findViewById<EditText>(R.id.mobileUP)
        val uTypeUpdate = findViewById<EditText>(R.id.uTypeUp)
        val dateUpdate = findViewById<EditText>(R.id.dOBUp)
        val districtUpdate = findViewById<EditText>(R.id.districtUp)
        val search =findViewById<ImageView>(R.id.searchicon)

        search.setOnClickListener{
            dbconnection = FirebaseDatabase.getInstance().getReference("user profile")
            val id = nameUpdate.text.toString()
            dbconnection.child(id).get().addOnSuccessListener {
                if(it.exists()){
                    val district = it.child("district1").value
                    val mobile =it.child("mobile1").value
                    val date =it.child("date1").value
                    val uType =it.child("uType1").value
                    val name=it.child("name1" ).value
                    mobileUpdate.setText(mobile.toString())
                    uTypeUpdate.setText(uType.toString())
                    dateUpdate.setText(date.toString())
                    districtUpdate.setText(district.toString())
                    nameUpdate1.setText(name.toString())
                    nameUpdate2.setText(name.toString())


                }else{
                    Toast.makeText(this,"Data is deleted or not exist", Toast.LENGTH_LONG).show()
                }
            }.addOnFailureListener {
                Toast.makeText(this,"failed. Try again", Toast.LENGTH_LONG).show()
            }
        }

        delete.setOnClickListener {

            val id  = nameUpdate.text.toString()

            dbconnection = FirebaseDatabase.getInstance().getReference("user profile")
            dbconnection.child(id).removeValue().addOnSuccessListener {

                nameUpdate.text.clear()
                nameUpdate1.text.clear()

                mobileUpdate.text.clear()
                uTypeUpdate.text.clear()
                districtUpdate.text.clear()
                dateUpdate.text.clear()
                Toast.makeText(this,"delete success", Toast.LENGTH_LONG).show()
                val jk =Intent(this,MainActivity::class.java)
                startActivity(jk)
            }.addOnFailureListener {
                Toast.makeText(this,"Delete failed", Toast.LENGTH_LONG).show()
            }
        }

        update.setOnClickListener {
            dbconnection = FirebaseDatabase.getInstance().getReference("user profile")
            val name2  = nameUpdate1.text.toString()
            val uType =  uTypeUpdate.text.toString()
            val mobile= mobileUpdate.text.toString()
            val district = districtUpdate.text.toString()
            val date = dateUpdate.text.toString()

            val updataedata = mapOf<String,String>(
                name2 to "name1",
                "name1" to name2,
                "mobile1" to mobile,
                "uType1" to  uType,
                "district1" to district,
                "date1" to date

            )

            dbconnection.child(name2).updateChildren(updataedata).addOnSuccessListener {

                nameUpdate1.text.clear()
                nameUpdate.text.clear()
                mobileUpdate.text.clear()
                uTypeUpdate.text.clear()
                districtUpdate.text.clear()
                dateUpdate.text.clear()

                Toast.makeText(this,"Update Successfull", Toast.LENGTH_LONG).show()
            }
                .addOnFailureListener {
                    Toast.makeText(this,"update not connectred", Toast.LENGTH_LONG).show()
                }
        }


    }
}