package com.example.busticket

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import kotlin.random.Random

class QRticket : AppCompatActivity() {

    var bitmap: Bitmap?= null
    private lateinit var  image  :ImageView
    private lateinit var  from :TextView
    private lateinit var  To :TextView
    private lateinit var  totale :TextView
    private  lateinit var numberOfpassenger : TextView
    private lateinit var dbcon : DatabaseReference



   var js :Int =0
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrticket)
        image = findViewById(R.id.Qrimageview)

        from = findViewById(R.id.fromBushalt)
        To = findViewById(R.id.Totextviwe)
        totale = findViewById(R.id.Totale)
        numberOfpassenger =findViewById(R.id.passengerNumber)
        val bundle : Bundle? = intent.extras

      val   From = bundle!!.getString("From")
        val TO =bundle.getString("TOhatte")
      val Totales = bundle.getString("Totale").toString()
       // val Totales = intent.getStringExtra("Totale")
      val  passenger = bundle.getString("number").toString()

        from.text = From
        To.text = TO
        totale.text = Totales
        numberOfpassenger.text =passenger
    // js = js+ 1
        js = Random.nextInt(6)
       if(js == null){
           Toast.makeText(this,"eneter any thing ", Toast.LENGTH_LONG).show()
       }else{
           val wrie = QRCodeWriter()

           try{

               val bitmax = wrie.encode(js.toString(), BarcodeFormat.QR_CODE,512,512)
               val width = bitmax.width
               val heigth = bitmax.height
               val bmp = Bitmap.createBitmap(width,heigth,Bitmap.Config.RGB_565)

               for(x in  0 until width){

                   for(y in  0 until heigth){
                       bmp.setPixel(x,y,if(bitmax[x,y]) Color.BLACK else Color.WHITE)
                   }
               }
               image.setImageBitmap(bmp)
           }catch (e: WriterException){
               e.printStackTrace()
           }
       }

        var pay = findViewById<Button>(R.id.Paybtn)

        pay.setOnClickListener {
            val TicleNo = js.toString()
            val nopassenge =passenger
            val stathalt =From
            val endhalt = TO
            val totale =Totales
            dbcon = FirebaseDatabase.getInstance().getReference("passengerTicket")
            val addpsanger = tickeDetails(TicleNo,nopassenge,stathalt,endhalt,totale)
            dbcon.child(TicleNo).setValue(addpsanger).addOnSuccessListener {

                val go =Intent(this@QRticket,displayeHalt::class.java)
                startActivity(go)

            }.addOnFailureListener {
                Toast.makeText(this,"data dase ading not complete",Toast.LENGTH_LONG).show()
            }
        }

    }
}