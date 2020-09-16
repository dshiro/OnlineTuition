package com.example.onlinetuition

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class DebitCreditForm : AppCompatActivity() {

    lateinit var editTextTextPersonName: EditText
    lateinit var editTextNumberCard: EditText
    lateinit var editTextDate: EditText
    lateinit var editTextNumberCCV: EditText
    lateinit var buttonPay: Button
    lateinit var textViewtotalPrice:TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debit_credit_form)


        editTextTextPersonName = findViewById(R.id.editTextTextPersonName)
        editTextNumberCard = findViewById(R.id.editTextNumberCard)
        editTextDate = findViewById(R.id.editTextDate)
        editTextNumberCCV = findViewById(R.id.editTextNumberCCV)
        buttonPay = findViewById(R.id.buttonPay)
        textViewtotalPrice = findViewById(R.id.textViewtotalPrice)


        buttonPay.setOnClickListener {
            saveCreditDebitCard()
        }


/*        var database = FirebaseDatabase .getInstance() .reference

        buttonPay.setOnClickListener {
            var name = editTextTextPersonName .text.toString()
            var cardNumber = editTextNumberCard .text.toString().toInt()
            var expiration = editTextDate .text.toString().toInt()
            var ccv = editTextNumberCCV .toString().toInt()
            database.child("payment/").push().setValue(CreditDebitCard (name,cardNumber,expiration,ccv))
        }*/

        //get data from intent
        val totalPrice = intent.getIntExtra("Total Price", 0)

        //textview
        val textTotal = findViewById<TextView>(R.id.textViewtotalPrice)

        //setTextView
        textTotal.text = "Total Price: RM $totalPrice"

    }

        private fun saveCreditDebitCard(){
            val name = editTextTextPersonName.text.toString().trim()
            val cardNumber = editTextNumberCard .text.toString().trim()
            val expiration = editTextDate .text.toString().trim()
            val ccv = editTextNumberCCV .text.toString().trim()
            val totalPrice = textViewtotalPrice.text.toString().trim()

            if(name.isEmpty()){
                editTextTextPersonName.error = "Please enter a name"
                return
            }
            if(cardNumber.isEmpty()){
                editTextNumberCard.error = "Please enter card number"
                return
            }
            if(expiration.isEmpty()){
                editTextDate.error = "Please enter card expiration"
                return
            }
            if(ccv.isEmpty()){
                editTextNumberCCV.error = "Please enter ccv"
                return
            }


           val ref = FirebaseDatabase.getInstance().getReference("Payment/")
           val paymentId =  ref.push().key

            val payment = CreditDebitCard(paymentId, name, cardNumber, expiration, ccv, totalPrice)

            ref.child(paymentId.toString()).setValue(payment).addOnCompleteListener {
                Toast.makeText(applicationContext , "Payment successful", Toast.LENGTH_LONG).show()
            }

        }


}