package com.example.onlinetuition

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

/*class ClassAdapter(mCtx: Context, layoutResId: Int, classList: List<ClassList> ) :ArrayAdapter<ClassList>(mCtx, layoutResId, classList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx);
        val view:View = layoutInflater.inflate(layoutResId, null);

        val textViewName = view.findViewById<TextView>(R.id.textViewName);
        val class = classList[position]

        textViewName.text = class.name

                return view;
    }
}*/