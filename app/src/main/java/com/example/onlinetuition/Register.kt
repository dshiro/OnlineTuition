package com.example.onlinetuition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity() {

    lateinit var mRegisterBtn: Button
    lateinit var mRegisterName: EditText
    lateinit var mRegisterEmail: EditText
    lateinit var mRegisterPassword: EditText
    lateinit var mRegisterPhone: EditText
    lateinit var mRegisterRace: EditText
    lateinit var mRegisterDate: EditText
    lateinit var mToLogin: TextView

    lateinit var mAuth: FirebaseAuth
    lateinit var ref: DatabaseReference
    var userID: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mRegisterBtn = findViewById(R.id.registerBtn)
        mRegisterName = findViewById(R.id.fullName)
        mRegisterEmail = findViewById(R.id.email)
        mRegisterPassword = findViewById(R.id.password)
        mRegisterPhone = findViewById(R.id.phone)
        mRegisterRace = findViewById(R.id.race)
        mRegisterDate = findViewById(R.id.datejoin)
        mToLogin = findViewById(R.id.goToLogin)

        mAuth = FirebaseAuth.getInstance()

        mToLogin.setOnClickListener {
            val toLogin = Intent(applicationContext, Login::class.java)
            startActivity(toLogin)
        }

        mRegisterBtn.setOnClickListener {
            val name = mRegisterName.text.toString().trim()
            val email = mRegisterEmail.text.toString().trim()
            val password = mRegisterPassword.text.toString().trim()
            val phone = mRegisterPhone.text.toString().trim()
            val race = mRegisterRace.text.toString().trim()
            val date = mRegisterDate.text.toString().trim()
            val rating: String = "0"

            if (TextUtils.isEmpty(name)) {
                mRegisterName.error = "Please Enter Your Name"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(email)) {
                mRegisterEmail.error = "Please Enter Email"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                mRegisterPassword.error = "Please Enter Password"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(phone)) {
                mRegisterPhone.error = "Please Enter Your Phone Number"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(race)) {
                mRegisterPhone.error = "Please Enter Your Race"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(date)) {
                mRegisterPhone.error = "Please Enter Date of Join"
                return@setOnClickListener
            }
            //createUser(email, password)
            else {
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            userID = mAuth.currentUser!!.uid
                            ref = FirebaseDatabase.getInstance().reference.child("User")
                                .child("/Student/$userID")
                            val user =
                                User(userID, name, email, password, phone, race, date, rating)

                            ref.setValue(user).addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val toLogin = Intent(this, Login::class.java)
                                    startActivity(toLogin)
                                    finish()
                                } else {
                                    Toast.makeText(applicationContext, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                                }

                            }
                        } else {
                            Toast.makeText(applicationContext, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                        }

                    }
            }

        }
    }
}