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

class Login : AppCompatActivity() {

    lateinit var mLoginBtn : Button
    lateinit var mLoginEmail : EditText
    lateinit var mLoginPassword : EditText
    lateinit var mToRegister : TextView

    lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mLoginBtn = findViewById(R.id.loginBtn)
        mLoginEmail = findViewById(R.id.email2)
        mLoginPassword = findViewById(R.id.password2)
        mToRegister = findViewById(R.id.goToRegister)

        mAuth = FirebaseAuth.getInstance()

        mToRegister.setOnClickListener{
            val toRegister = Intent (this, Register::class.java)
            startActivity(toRegister)
        }


        mLoginBtn.setOnClickListener{
            val email = mLoginEmail.text.toString().trim()
            val password = mLoginPassword.text.toString().trim()

            if(TextUtils.isEmpty(email)){
                mLoginEmail.error = "Please Enter Email"
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(password)){
                mLoginPassword.error = "Please Enter Password"
                return@setOnClickListener
            }

            loginUser(email, password)
        }
    }

    private fun loginUser(email: String, password: String) {


        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {task ->
                if (task.isSuccessful){
                    val loginID: String = mAuth.currentUser!!.uid
                    val toHome = Intent(this, MainActivity::class.java)
                    toHome.putExtra("LoginID", loginID)
                    startActivity(toHome)
                    finish()
                } else {
                    Toast.makeText(applicationContext,"Authentication Failed",Toast.LENGTH_SHORT).show();
                }
            }

    }

}