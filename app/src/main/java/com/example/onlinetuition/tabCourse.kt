package com.example.onlinetuition

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class tabCourse : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tab_course, container, false)

        val firebaseDB = FirebaseDatabase.getInstance().reference.child("User")
        firebaseDB.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var arrCourse = ArrayList<String>()

                for (postSnapshot in dataSnapshot.children) {
                    Log.println(Log.INFO, "debug_course", postSnapshot.toString())
                    arrCourse.add(postSnapshot.child("course").value.toString())
                }

                val courseIT = arrCourse.count {it == "IT"}
                val courseAccount = arrCourse.count {it == "Account"}
                val courseBusiness = arrCourse.count {it == "Business"}
                val courseElectronic = arrCourse.count {it == "Electronic"}
                val courseGeography = arrCourse.count {it == "Geography"}
                val courseMathematics = arrCourse.count {it == "Mathematics"}
                val courseMultimedia = arrCourse.count {it == "Multimedia"}
                val courseScience = arrCourse.count {it == "Science"}

                val barEntry = ArrayList<BarEntry>();
                barEntry.add(BarEntry(0f, courseIT.toFloat()));
                barEntry.add(BarEntry(1f, courseAccount.toFloat()));
                barEntry.add(BarEntry(2f, courseBusiness.toFloat()));
                barEntry.add(BarEntry(3f, courseElectronic.toFloat()));
                barEntry.add(BarEntry(4f, courseGeography.toFloat()));
                barEntry.add(BarEntry(5f, courseMathematics.toFloat()));
                barEntry.add(BarEntry(6f, courseMultimedia.toFloat()));
                barEntry.add(BarEntry(7f, courseScience.toFloat()));

                val barChart: BarChart = view.findViewById(R.id.bar_chart);
                val barDataSet = BarDataSet(barEntry, "Course");
                val barData = BarData(barDataSet)
                barChart.data = barData;
                barChart.invalidate()

                val xAxisLabel: ArrayList<String> = ArrayList()
                xAxisLabel.add("IT")
                xAxisLabel.add("Account")
                xAxisLabel.add("Business")
                xAxisLabel.add("Electronic")
                xAxisLabel.add("Geography")
                xAxisLabel.add("Mathematics")
                xAxisLabel.add("Multimedia")
                xAxisLabel.add("Science")

                val xAxis = barChart.xAxis
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.setDrawGridLines(false)
                //TODO - add label handler
                //TODO - list view
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })

        return view
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            tabCourse().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}