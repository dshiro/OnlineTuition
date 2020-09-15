package com.example.onlinetuition

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStatistics = findViewById<Button>(R.id.button_statistics)
        buttonStatistics.setOnClickListener{
            val toStatistic = Intent(this, StatisticMain::class.java)
            //toStatistic.putExtra("name", "value")
            startActivity(toStatistic)
        }

        val buttonSelectCourse = findViewById<Button>(R.id.button_course)
        buttonSelectCourse.setOnClickListener{
            val toCourse = Intent(this, CourseSelect::class.java)
            startActivity(toCourse)
        }



        val buttonClassroom = findViewById<Button>(R.id.button_classroom)
        buttonClassroom.setOnClickListener{
            val toClass = Intent(this, ClassList::class.java)
            startActivity(toClass)
        }
    }
}