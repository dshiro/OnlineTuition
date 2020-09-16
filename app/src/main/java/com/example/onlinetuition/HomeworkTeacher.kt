package com.example.onlinetuition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_homework_teacher.*
import java.lang.StringBuilder

class HomeworkTeacher : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homework_teacher)
        //var addDatabase = FirebaseDatabase.getInstance().reference.child("Course")
        var database = FirebaseDatabase.getInstance().reference.child("Course").child("Account")

        btnAdd.setOnClickListener{

            var homework = editText1.text.toString()
            var title = editText2.text.toString()

            database.child("$homework").setValue(title)
        }

        var getData = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var sb = StringBuilder()
                for(i in snapshot.children) {

                    var homework = i.getValue()

                    //sb.append("${i.key} $subject\n ")
                    sb.append("$homework\n____________________________________\n")
                }
                homework_teacher.setText(sb)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
        database.addValueEventListener(getData)
        database.addListenerForSingleValueEvent(getData)
    }
}


