package com.example.onlinetuition

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter


class CourseAdapter (val mCtx: Context, val layoutResId: Int, val courseList:List<Course> )
    : ArrayAdapter<Course>(mCtx, layoutResId, courseList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx);
        val view: View = layoutInflater.inflate(layoutResId, null)

        val course = courseList[position]

        return view;
    }
}