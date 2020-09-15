package com.example.onlinetuition

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        val buttonLogout = findViewById<Button>(R.id.button_logout)
        buttonLogout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val toLogin = Intent ( this, Login::class.java)
            startActivity(toLogin)
            finish()
        }


        val buttonStatistics = findViewById<Button>(R.id.button_statistics)
        buttonStatistics.setOnClickListener{
            val toStatistic = Intent(this, StatisticMain::class.java)
            //toStatistic.putExtra("name", "value")
            startActivity(toStatistic)
        }

        val buttonSelectCourse = findViewById<Button>(R.id.button_course)
        buttonSelectCourse.setOnClickListener{
            val toCourse = Intent(this, CourseSelect::class.java)
            val loginID = intent.getStringExtra("LoginID")
            toCourse.putExtra("LoginID", loginID)
            startActivity(toCourse)
        }



        val buttonClassroom = findViewById<Button>(R.id.button_classroom)
        buttonClassroom.setOnClickListener{
            val toClass = Intent(this, ClassList::class.java)
            startActivity(toClass)
        }

        val buttonRating = findViewById<Button>(R.id.btnRating)
        buttonRating.setOnClickListener{
            val toRating = Intent(this, student_rating::class.java)
            startActivity(toRating)
        }
    }
    override fun onStart(){
        super.onStart()

        val currentUser = FirebaseAuth.getInstance().currentUser
        if(currentUser == null){
            val toLogin = Intent ( applicationContext, Login::class.java)
            startActivity(toLogin)
            finish()
        } else {
            Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_SHORT).show();
        }
    }
}