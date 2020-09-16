package com.example.onlinetuition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RatingBar
import android.widget.Toast
import androidx.annotation.NonNull
import com.google.firebase.database.*
import java.lang.Exception

class student_rating : AppCompatActivity() {
    lateinit var ref: DatabaseReference
    lateinit var userid: String
    lateinit var ratingBar: RatingBar
    private var ratingval:  Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_rating)

        //userid = "fe26WUbXAFXw9NiDbzacWGHXEea2"
        userid = intent.getStringExtra("LoginID").toString()

        if (userid == "null") {
            Toast.makeText(applicationContext , "Unable to rate - no user ID found.", Toast.LENGTH_LONG).show()
            findViewById<View>(R.id.button).isEnabled = false
        }

        else {
            ratingBar = findViewById<View>(R.id.ratingBar) as RatingBar

            ref = FirebaseDatabase.getInstance().getReference("User").child("Student").child(userid)
            ref.addValueEventListener(object: ValueEventListener {
                @Override
                override fun onDataChange(@NonNull dataSnapshot: DataSnapshot) {
                    try {
                        ratingval = dataSnapshot.child("rating").value.toString().toInt()
                        ratingBar.rating = ratingval.toFloat()
                    }
                    catch (ex: Exception){
                        Log.e("rating_conversion", ex.toString())
                        Toast.makeText(applicationContext , "Previous rating retrieve failed.", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) { }
            })
        }
    }


    fun click(view: View){
        ratingval = ratingBar.rating.toInt()
        ref.child("rating").setValue(ratingval)
        Toast.makeText(applicationContext , "Submitted, Thanks!", Toast.LENGTH_LONG).show()
    }
}