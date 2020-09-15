package com.example.onlinetuition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RatingBar
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class student_rating : AppCompatActivity() {
    var ratingval: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_rating)
    }

    fun click(view: View){
        val ratingBar = findViewById<View>(R.id.ratingBar) as RatingBar
        ratingval = ratingBar.rating.toInt()

        //val userid = intent.getStringExtra("UID")
        val userid = "fe26WUbXAFXw9NiDbzacWGHXEea2"


        if (userid == null) {
            Toast.makeText(applicationContext , "Submit Failed - User ID missing", Toast.LENGTH_LONG).show()
        }
        else {
            val ref = FirebaseDatabase.getInstance().getReference("User")
                .child("Student")
                .child(userid)
                .child("rating")

            ref.setValue(ratingval)
        }
    }
}