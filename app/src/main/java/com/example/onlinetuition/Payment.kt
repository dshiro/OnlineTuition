package com.example.onlinetuition

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Payment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        //  setOf(supportActionBar?.setTitle("Home Page"))
        setOf(supportActionBar?.setDisplayHomeAsUpEnabled(true))




        val buttonBanking = findViewById<Button>(R.id.buttonBankIn)
        buttonBanking.setOnClickListener {
            val toBankin = Intent(this, BankIn::class.java)
            startActivity(toBankin)
        }

        val buttonCard = findViewById<Button>(R.id.buttonCard)
        buttonCard.setOnClickListener {
            val toCard = Intent(this, DebitCreditForm::class.java)
            startActivity(toCard)
        }

    }
}