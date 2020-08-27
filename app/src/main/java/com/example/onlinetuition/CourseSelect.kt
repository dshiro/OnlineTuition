package com.example.onlinetuition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class CourseSelect : AppCompatActivity(), AdapterView.OnItemClickListener {


    private var listView:ListView ? = null
    private var arrayAdapter:ArrayAdapter<String> ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_select)

        listView = findViewById(R.id.list_view)
        arrayAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_multiple_choice, resources.getStringArray(R.array.subject_item))

        listView?.adapter = arrayAdapter
        listView?.choiceMode = ListView.CHOICE_MODE_MULTIPLE
        listView?.onItemClickListener = this

    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

//        var items:String = p0?.getItemAtPosition(p2) as String
//        Toast.makeText(applicationContext,
//            "Subject Course : $items",
//            Toast.LENGTH_SHORT).show()


    }


}








