package com.example.onlinetuition

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.onlinetuition.databinding.FragmentTabCourseBinding
import com.example.onlinetuition.databinding.FragmentTabStudentBinding
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

class tabStudent : Fragment() {
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

        val fragmentTabStudentBinding =  DataBindingUtil.inflate<FragmentTabStudentBinding>(inflater, R.layout.fragment_tab_student, container, false)
        val view = fragmentTabStudentBinding.root

        val firebaseDB = FirebaseDatabase.getInstance().reference.child("User").child("Student")
        firebaseDB.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var arrRace = ArrayList<String>()

                for (postSnapshot in dataSnapshot.children) {
                    //Log.println(Log.INFO, "debug_race", postSnapshot.toString())
                    arrRace.add(postSnapshot.child("race").value.toString())
                }

                val total:Double = arrRace.count().toDouble()
                val raceChinese = arrRace.count { it == "Chinese" }
                val raceIndia = arrRace.count { it == "Indian" }
                val raceMalay = arrRace.count { it == "Malay" }
                val raceInter = arrRace.count { it == "International" }
                val raceBangladesh = arrRace.count { it == "Bangladesh" }
                val raceMyanmar = arrRace.count { it == "Myanmar" }

                val raceChinese_per = String.format("%.2f", (raceChinese / total) * 100) + "%"
                val raceIndia_per = String.format("%.2f", (raceIndia / total) * 100) + "%"
                val raceMalay_per = String.format("%.2f", (raceMalay / total) * 100) + "%"
                val raceInter_per = String.format("%.2f", (raceInter / total) * 100) + "%"
                val raceBangladesh_per = String.format("%.2f", (raceBangladesh / total) * 100) + "%"
                val raceMyanmar_per = String.format("%.2f", (raceMyanmar / total) * 100) + "%"

                val barEntry = ArrayList<BarEntry>();
                barEntry.add(BarEntry(0f, raceChinese.toFloat()));
                barEntry.add(BarEntry(1f, raceMalay.toFloat()));
                barEntry.add(BarEntry(2f, raceIndia.toFloat()));
                barEntry.add(BarEntry(3f, raceInter.toFloat()));
                barEntry.add(BarEntry(4f, raceBangladesh.toFloat()));
                barEntry.add(BarEntry(5f, raceMyanmar.toFloat()));

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

                fragmentTabStudentBinding.raceData =
                    RaceData(
                        raceChinese.toString(), raceChinese_per,
                        raceMalay.toString(), raceMalay_per,
                        raceIndia.toString(), raceIndia_per,
                        raceInter.toString(), raceInter_per,
                        raceBangladesh.toString(), raceBangladesh_per,
                        raceMyanmar.toString(), raceMyanmar_per
                    );

            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })

        return view
    }



    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            tabStudent().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}