package com.example.onlinetuition

import android.os.Bundle
import android.util.SparseBooleanArray
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase


class CourseSelect : AppCompatActivity() {


    lateinit var listView: ListView
    private var arrayAdapter: ArrayAdapter<String>? = null
    private var subject: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_select)
        setSupportActionBar(findViewById(R.id.toolbar_select_course))
        supportActionBar?.title = "Select Course (Multiple)"

        listView = findViewById(R.id.list_view)
        arrayAdapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_list_item_multiple_choice,
            resources.getStringArray(R.array.subject_item)
        )

        listView.adapter = arrayAdapter
        listView.choiceMode = ListView.CHOICE_MODE_MULTIPLE


        val confirmButton = findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            saveCourse()
        }


        listView.setOnItemClickListener { p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long ->
            subject += p0?.getItemAtPosition(p2) as String?


        }

/*        val checked: SparseBooleanArray = listView.getCheckedItemPositions()
        val size = checked.size() // number of name-value pairs in the array


        for (i in 0 until size) {
            val key = checked.keyAt(i)
            val value = checked[key]
            if (value) doSomethingWithSelectedIndex(key)
        }*/




    }

    private fun saveCourse() {
        val ref = FirebaseDatabase.getInstance().getReference("User").child("Student")
        val course = Course("$subject")

        ref.push().setValue(course)

/*        val toPayment = Intent(this, Payment::class.java)
intent.putExtra("Total Price",50)
        startActivity(toPayment)*/

    }





}








