package com.example.onlinetuition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.databinding.DataBindingUtil
import com.example.onlinetuition.databinding.FragmentTabCourseBinding
import com.google.firebase.database.*

import kotlinx.android.synthetic.main.activity_course_select.*

class CourseSelect : AppCompatActivity(), AdapterView.OnItemClickListener {

    private var listView: ListView? = null
    private var arrayAdapter: ArrayAdapter<String>? = null
    lateinit var ref: DatabaseReference
    lateinit var courseList: MutableList<Course>

/*    lateinit var listView: ListView*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_select)
        setSupportActionBar(findViewById(R.id.toolbar_select_course))
        supportActionBar?.title = "Select Course"

        listView = findViewById(R.id.list_view)
        arrayAdapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_list_item_multiple_choice,
            resources.getStringArray(R.array.subject_item)
        )

        listView?.adapter = arrayAdapter
        listView?.choiceMode = ListView.CHOICE_MODE_MULTIPLE
        listView?.onItemClickListener = this

/*    courseList = mutableListOf()
    ref = FirebaseDatabase.getInstance().getReference("Course")
    listView = findViewById(R.id.list_view)


    val adapter = CourseAdapter(applicationContext, R.layout.activity_course_select, courseList)
    listView.adapter = adapter*/


        val confirmButton = findViewById<Button>(R.id.confirmButton)

        confirmButton.setOnClickListener {
            saveCourse()
        }


    }

    private fun saveCourse() {
        val name = listView?.selectedItem.toString()
        val ref = FirebaseDatabase.getInstance().getReference("User").child("Student")

        val course = Course(name)

        ref.child("4").setValue(course)


    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var subject: String = parent?.getItemAtPosition(position) as String
        Toast.makeText(
            applicationContext,
            "Subject Course : $subject",
            Toast.LENGTH_SHORT
        ).show()
    }
}