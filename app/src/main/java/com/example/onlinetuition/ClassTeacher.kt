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
import kotlinx.android.synthetic.main.activity_class_teacher.*
import java.lang.StringBuilder

class ClassTeacher : AppCompatActivity() {

    private var listView: ListView? = null
    private var arrayAdapter: ArrayAdapter<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_teacher)

        listView = findViewById(R.id.list_teacher)
        arrayAdapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.class_teacher)
        )

        listView?.adapter = arrayAdapter

        list_teacher.setOnItemClickListener { parent: AdapterView<*>, view: View, position:Int, id:Long ->

            val toHomeworkTeacher = Intent(this, HomeworkTeacher::class.java)
            startActivity(toHomeworkTeacher)

        }

        /*var database = FirebaseDatabase.getInstance().reference.child("Course")

        var getData = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var sb = StringBuilder()
                for(i in snapshot.children) {

                    //var homework = i.child("homework").getValue()

                    //sb.append("${i.key} $subject\n ")
                    sb.append("${i.key}\n____________________________________\n")
                }
                list_class_teacher.setText(sb)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
        database.addValueEventListener(getData)
        database.addListenerForSingleValueEvent(getData)

        list_class_teacher.setOnClickListener {

            val toHomeworkTeacher = Intent(this, HomeworkTeacher::class.java)
            startActivity(toHomeworkTeacher)

        }*/
    }


}