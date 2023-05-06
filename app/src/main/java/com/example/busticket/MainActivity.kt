package com.example.busticket

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
   private lateinit var dbconnecte :DatabaseReference
    /*private lateinit var recycleBus : RecyclerView
    private lateinit var buslist : ArrayList<BusHallt>
    lateinit var  adapt : Myadapter
    lateinit var serch : ImageView*/
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      setContentView(R.layout.activity_main)
      val k = findViewById<Button>(R.id.nextx)
        val username = findViewById<EditText>(R.id.usenameEdt)
        val password = findViewById<EditText>(R.id.passwordEdt)
       val singin = findViewById<TextView>(R.id.singin)
        singin.setOnClickListener{
            val o = Intent(this,RegistewrUser::class.java)
            startActivity(o)
        }
      k.setOnClickListener {

          dbconnecte = FirebaseDatabase.getInstance().getReference("Users")
          val Username = username.text.toString()
          val AuthPassword = password.text.toString()
           dbconnecte.child(Username).get().addOnSuccessListener {
               if(it.exists()){
                   val auser = it.child("username").value
                   val email= it.child("email").value
                   val passw =it.child("passwored").value
                   val disname = it.child("displayename").value

                   val  p = disname.toString()
                   if ((Username ==auser) && (AuthPassword == passw)){
                       val os = Intent(this,bashborde::class.java)
                         os.putExtra("nickname",p)
                       startActivity(os)
                   }else{
                       Toast.makeText(this,"plese enter correcte",Toast.LENGTH_LONG).show()
                   }
               }else{
                   Toast.makeText(this,"Enter plese enter correcte",Toast.LENGTH_LONG).show()
               }
           }


      }
      /*
       val HaltId = findViewById<EditText>(R.id.haltid)
       val Haltname = findViewById<EditText>(R.id.haliname)
       val registerHaltbtn = findViewById<Button>(R.id.haltbtn1235)
       val delete = findViewById<Button>(R.id.deletebtn)
       val k = findViewById<Button>(R.id.button2236)
       k.setOnClickListener {
           val p =Intent(this,displayeHalt::class.java)
           startActivity(p)
       }
       serch = findViewById(R.id.serchbt)
       registerHaltbtn.setOnClickListener {

           var halltID =HaltId.text.toString()
           var halltname = Haltname.text.toString()

           if(halltID.isEmpty() && halltname.isEmpty()){

               Toast.makeText(this,"Enter Details correct liye",Toast.LENGTH_LONG).show()
           }else{
               addBus(halltID,halltname)
           }
       }
       serch.setOnClickListener{
           val HaltId = findViewById<EditText>(R.id.haltid)
           val halt :String = HaltId.text.toString()
           if(halt.isNotEmpty()){
               findhalt(halt)
           }else{
               Toast.makeText(this,"Enter valide details",Toast.LENGTH_LONG).show()
           }

       }
       delete.setOnClickListener {
           val HaltId = findViewById<EditText>(R.id.haltid)
           val deiete : String = HaltId.text.toString()
              Haltdelete(deiete)
       }

       //recycle()
       recycleBus =findViewById(R.id.mtrecycle)
       recycleBus.layoutManager = LinearLayoutManager(this)
       recycleBus.setHasFixedSize(true)

       buslist = arrayListOf()
       adapt = Myadapter(buslist)
       displayeBushalt()
  /* adapt.onItemclick ={
            val intent =Intent(this,BusTickeclculate::class.java)
          intent.putExtra("ticket",it)
       Toast.makeText(this,"work",Toast.LENGTH_LONG).show()
       startActivity(intent)
   }*/

   }



   private fun findhalt(halt: String) {
       val Haltname = findViewById<EditText>(R.id.haliname)

       dbconnecte = FirebaseDatabase.getInstance().getReference("BUSHalt")

       dbconnecte.child(halt).get().addOnSuccessListener {
           if(it.exists()){
               val bushalts = it.child("halltname").value
               Toast.makeText(this,"catch succefull",Toast.LENGTH_LONG).show()
               Haltname.setText(bushalts.toString())

           }else{
               Toast.makeText(this,"catch  not succefull",Toast.LENGTH_LONG).show()

           }
       }
   }
   private fun Haltdelete(deiete: String) {
       val HaltId = findViewById<EditText>(R.id.haltid)
       val Haltname = findViewById<EditText>(R.id.haliname)
       dbconnecte = FirebaseDatabase.getInstance().getReference("BUSHalt")
       dbconnecte.child(deiete).removeValue().addOnSuccessListener {
           Toast.makeText(this,"delete succefull",Toast.LENGTH_LONG).show()
          Haltname.text.clear()
           HaltId.text.clear()

       }.addOnFailureListener {
           Toast.makeText(this,"delete fail",Toast.LENGTH_LONG).show()
           Haltname.text.clear()
           HaltId.text.clear()
       }

   }


   fun addBus(halltID: String, halltname: String) {
        val HaltId = findViewById<EditText>(R.id.haltid)
        val Haltname = findViewById<EditText>(R.id.haliname)

       dbconnecte = FirebaseDatabase.getInstance().getReference("BUSHalt")
       var bushalt = BusHallt(halltID,halltname)
       dbconnecte.child(halltID).setValue(bushalt).addOnSuccessListener {

           Haltname.text.clear()
           HaltId.text.clear()
           Toast.makeText(this,"Succefull Add ",Toast.LENGTH_LONG).show()

       }.addOnFailureListener{
           Toast.makeText(this,"connection not works",Toast.LENGTH_LONG).show()
       }
   }
   private fun displayeBushalt() {
       dbconnecte = FirebaseDatabase.getInstance().getReference("BUSHalt")

       dbconnecte.addValueEventListener(object : ValueEventListener{
           override fun onDataChange(snapshot: DataSnapshot) {
              if(snapshot.exists()){

                  for (busSnapshor in snapshot.children){
                      val halt = busSnapshor.getValue(BusHallt::class.java)
                       buslist.add(halt!!)
                  }
                  var adp = Myadapter(buslist)
                  recycleBus.adapter =adp
                  adp.setonItemClickListener(object :Myadapter.onItemClickListener{
                      override fun inItemckick(position: Int) {
                          Toast.makeText(this@MainActivity,"you click item code works",Toast.LENGTH_LONG).show()
                          val intent =Intent(this@MainActivity,BusTickeclculate::class.java)
                          intent.putExtra("id",buslist[position].halltID)
                          intent.putExtra("name",buslist[position].halltname)
                           startActivity(intent)
                      }

                  })
               /*   adapt = Myadapter(buslist)
                  displayeBushalt()
                  adapt.onItemclick ={
                      val intent =Intent(this@MainActivity,BusTickeclculate::class.java)
                      intent.putExtra("ticket",it)

                      startActivity(intent)
                  }*/
              }
           }

           override fun onCancelled(error: DatabaseError) {
               TODO("Not yet implemented")
           }




       })
*/
    }
}

