package com.example.onlinetuition

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.onlinetuition.databinding.FragmentTabCourseBinding
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

        val fragmentTabCourseBinding =  DataBindingUtil.inflate<FragmentTabCourseBinding>(inflater, R.layout.fragment_tab_course, container, false)
        val view = fragmentTabCourseBinding.root

        val firebaseDB = FirebaseDatabase.getInstance().reference.child("User").child("Student")
        firebaseDB.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var arrCourse = ArrayList<String>()

                for (postSnapshot in dataSnapshot.children) {
                    Log.println(Log.INFO, "debug_course", postSnapshot.toString())
                    arrCourse.add(postSnapshot.child("course").value.toString())
                }

                val total:Double = dataSnapshot.childrenCount.toDouble()
                val courseIT = arrCourse.count {it == "IT"}
                val courseAccount = arrCourse.count {it == "Account"}
                val courseBusiness = arrCourse.count {it == "Business"}
                val courseElectronic = arrCourse.count {it == "Electronic"}
                val courseGeography = arrCourse.count {it == "Geography"}
                val courseMathematics = arrCourse.count {it == "Mathematics"}
                val courseMultimedia = arrCourse.count {it == "Multimedia"}
                val courseScience = arrCourse.count {it == "Science"}

                val courseIT_per = String.format("%.2f", (courseIT / total) * 100) + "%"
                val courseAccount_per = String.format("%.2f", (courseAccount / total) * 100) + "%"
                val courseBusiness_per = String.format("%.2f", (courseBusiness / total) * 100) + "%"
                val courseElectronic_per = String.format("%.2f", (courseElectronic / total) * 100) + "%"
                val courseGeography_per = String.format("%.2f", (courseGeography / total) * 100) + "%"
                val courseMathematics_per = String.format("%.2f", (courseMathematics / total) * 100) + "%"
                val courseMultimedia_per = String.format("%.2f", (courseMultimedia / total) * 100) + "%"
                val courseScience_per = String.format("%.2f", (courseScience / total) * 100) + "%"

                val barEntry = ArrayList<BarEntry>();
                barEntry.add(BarEntry(0f, courseIT.toFloat()));
                barEntry.add(BarEntry(1f, courseAccount.toFloat()));
                barEntry.add(BarEntry(2f, courseBusiness.toFloat()));
                barEntry.add(BarEntry(3f, courseElectronic.toFloat()));
                barEntry.add(BarEntry(4f, courseGeography.toFloat()));
                barEntry.add(BarEntry(5f, courseMathematics.toFloat()));
                barEntry.add(BarEntry(6f, courseMultimedia.toFloat()));
                barEntry.add(BarEntry(7f, courseScience.toFloat()));

                val barDataSet = BarDataSet(barEntry, "");
                barDataSet.setDrawValues(false)
                barDataSet.color = R.color.colorPrimary
                val barData = BarData(barDataSet)

                val barChart: BarChart = view.findViewById(R.id.bar_chart);
                barChart.description.isEnabled = false
                barChart.legend.isEnabled = false
                barChart.setTouchEnabled(false)
                barChart.data = barData;
                barChart.invalidate()

                val xAxis = barChart.xAxis
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.setDrawGridLines(false)

                val leftAxis = barChart.axisLeft
                val rightAxis = barChart.axisRight
                leftAxis.setDrawAxisLine(true)
                leftAxis.setDrawGridLines(true)
                leftAxis.axisMinimum = 0f;
                rightAxis.setDrawAxisLine(true)
                rightAxis.setDrawGridLines(true)
                rightAxis.axisMinimum = 0f;

                fragmentTabCourseBinding.courseData =
                    CourseData(
                        courseIT.toString(), courseIT_per,
                        courseAccount.toString(), courseAccount_per,
                        courseBusiness.toString(), courseBusiness_per,
                        courseElectronic.toString(), courseElectronic_per,
                        courseGeography.toString(), courseGeography_per,
                        courseMathematics.toString(), courseMathematics_per,
                        courseMultimedia.toString(), courseMultimedia_per,
                        courseScience.toString(), courseScience_per
                    );


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