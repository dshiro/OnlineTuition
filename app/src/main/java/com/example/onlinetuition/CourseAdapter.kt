package com.example.onlinetuition

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.CheckedTextView


class CourseAdapter (val mCtx: Context, val layoutResId: Int, val courseList:List<String> )
    : ArrayAdapter<String>(mCtx, layoutResId, courseList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx);
        val view: View = layoutInflater.inflate(layoutResId, null)
        val courseText = view.findViewById<TextView>(R.id.courseName)
        val course = courseList[position]

        courseText.text = course
        return view;
    }
}