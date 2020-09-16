package com.example.onlinetuition

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.SparseBooleanArray
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.ColorUtils
import com.google.firebase.database.FirebaseDatabase


class CourseSelect : AppCompatActivity() {


    lateinit var listView: ListView
    private var arrayAdapter: ArrayAdapter<String>? = null
    var courseList = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_select)
        setSupportActionBar(findViewById(R.id.toolbar_select_course))
        supportActionBar?.title = "Select Course (Multiple)"


        listView = findViewById(R.id.list_view)
        arrayAdapter = object: ArrayAdapter<String>(applicationContext, android.R.layout.simple_list_item_multiple_choice, resources.getStringArray(R.array.subject_item)){
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                if (position %2 == 0){
                    view.setBackgroundColor(Color.parseColor("#93766c"))
                }else{
                    view.setBackgroundColor(Color.WHITE)
                }
                return view
            }
        }

        listView.adapter = arrayAdapter
        listView.choiceMode = ListView.CHOICE_MODE_MULTIPLE


        val confirmButton = findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
           /* passTotalPrice()*/
            saveCourse()

        }


        listView.setOnItemClickListener { p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long ->
            val clickedText = (p0?.getItemAtPosition(p2)).toString()
            val isContain = courseList.any { it == clickedText }

            if (isContain) {
                courseList.remove(clickedText)
            } else {
                courseList.add(clickedText)
            }
        }
    }

/*    private fun saveCourse() {
        val valuetoadd = courseList.distinct().joinToString(separator = ",")
        val userID = intent.getStringExtra("LoginID").toString()
        val ref = FirebaseDatabase.getInstance().getReference("User").child("Student").child(userID)
        /*val course = Course(valuetoadd)*/

        ref.push().setValue("course: $valuetoadd")

    }

    private fun passTotalPrice(){
        val checked: SparseBooleanArray = listView.checkedItemPositions
        val size = checked.size()
        val totalPrice = size * 50

        val toPayment = Intent(this, BankIn::class.java)
      
        toPayment.putExtra("Total Price", totalPrice.toString().toInt())
        startActivity(toPayment)
    }*/

    private fun saveCourse() {
        val valuetoadd = courseList.distinct().joinToString(separator = ",")
        val ref = FirebaseDatabase.getInstance().getReference("User").child("Student")
        val course = Course(valuetoadd)

        ref.push().setValue(course)


        val checked: SparseBooleanArray = listView.checkedItemPositions
        val size = checked.size()
        val totalPrice = size * 50

        val toPayment = Intent(this, Payment::class.java)
        toPayment.putExtra("Total Price", totalPrice)
        startActivity(toPayment)
    }

}








