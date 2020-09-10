package com.example.onlinetuition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_class_list.*
import kotlinx.android.synthetic.main.activity_class_list.list_class
import kotlinx.android.synthetic.main.activity_homework.*

class Homework : AppCompatActivity() {

    private var listView: ListView? = null
    private var arrayAdapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homework)


        listView = findViewById(R.id.list_homework)
        arrayAdapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.homework_item)
        )

        listView?.adapter = arrayAdapter

        list_homework.setOnItemClickListener { parent: AdapterView<*>, view: View, position:Int, id:Long ->

            val toHomeworkSubmission = Intent(this, HomeworkSubmission::class.java)
            startActivity(toHomeworkSubmission)

        }
    }
}