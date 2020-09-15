package com.example.onlinetuition

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_class_list.*
import kotlinx.android.synthetic.main.activity_homework_submission.*
import java.lang.StringBuilder
import java.util.*


class ClassList : AppCompatActivity() {
    //private var listView:ListView ? = null
    //private var arrayAdapter:ArrayAdapter<String> ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_list)

        /*listView = findViewById(R.id.list_class)
        arrayAdapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.class_item)
        )

        listView?.adapter = arrayAdapter

        list_class.setOnItemClickListener { parent:AdapterView<*>, view:View, position:Int, id:Long ->

                val toHomework = Intent(this, Homework::class.java)
                startActivity(toHomework)

        }*/
        var database = FirebaseDatabase.getInstance().reference.child("Course")

        var getData = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var sb = StringBuilder()
                for(i in snapshot.children) {

                    //var homework = i.child("homework").getValue()

                    //sb.append("${i.key} $subject\n ")
                    sb.append("${i.key}\n____________________________________\n")
                }
                list_class.setText(sb)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
        database.addValueEventListener(getData)
        database.addListenerForSingleValueEvent(getData)

        list_class.setOnClickListener {

            val toHomework = Intent(this, Homework::class.java)
            startActivity(toHomework)

        }
    }
}