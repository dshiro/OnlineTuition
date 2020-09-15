package com.example.onlinetuition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_class_list.*
import kotlinx.android.synthetic.main.activity_class_list.list_class
import kotlinx.android.synthetic.main.activity_homework.*
import kotlinx.android.synthetic.main.activity_homework_submission.*
import java.lang.StringBuilder

class Homework : AppCompatActivity() {

    //private var listView: ListView? = null
    //private var arrayAdapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homework)


        /*listView = findViewById(R.id.list_homework)
        arrayAdapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.homework_item)
        )

        listView?.adapter = arrayAdapter

        list_homework.setOnItemClickListener { parent: AdapterView<*>, view: View, position:Int, id:Long ->

            val toHomeworkSubmission = Intent(this, HomeworkSubmission::class.java)
            startActivity(toHomeworkSubmission)

        }*/

        var database = FirebaseDatabase.getInstance().reference.child("Course")

        var getData = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var sb = StringBuilder()
                for(i in snapshot.children) {

                    var homework = i.child("homework").getValue()

                    //sb.append("${i.key} $subject\n ")
                    sb.append("$homework\n____________________________________\n\n")
                }
                homework.setText(sb)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
        database.addValueEventListener(getData)
        database.addListenerForSingleValueEvent(getData)

        homework.setOnClickListener() {

            val toHomeworkSubmission = Intent(this, HomeworkSubmission::class.java)
            startActivity(toHomeworkSubmission)

        }
    }
}