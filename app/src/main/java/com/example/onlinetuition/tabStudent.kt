package com.example.onlinetuition

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
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

        val view = inflater.inflate(R.layout.fragment_tab_student, container, false)

        val firebaseDB = FirebaseDatabase.getInstance().reference.child("User").child("Student")
        firebaseDB.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var arrRace = ArrayList<String>()

                for (postSnapshot in dataSnapshot.children) {
                    Log.println(Log.INFO, "debug_race", postSnapshot.toString())
                    arrRace.add(postSnapshot.child("race").value.toString())
                }

                val raceChinese = arrRace.count { it == "Chinese" }
                val raceIndia = arrRace.count { it == "Indian" }
                val raceMalay = arrRace.count { it == "Malay" }
                val raceInter = arrRace.count { it == "International" }
                val raceBangladesh = arrRace.count { it == "Bangladesh" }
                val raceMyanmar = arrRace.count { it == "Myanmar" }


                val barEntry = ArrayList<BarEntry>();
                barEntry.add(BarEntry(0f, raceChinese.toFloat()));
                barEntry.add(BarEntry(1f, raceMalay.toFloat()));
                barEntry.add(BarEntry(2f, raceIndia.toFloat()));
                barEntry.add(BarEntry(3f, raceInter.toFloat()));
                barEntry.add(BarEntry(4f, raceBangladesh.toFloat()));
                barEntry.add(BarEntry(5f, raceMyanmar.toFloat()));

                val barChart:BarChart = view.findViewById(R.id.bar_chart);
                val barDataSet = BarDataSet(barEntry, "Race");
                val barData = BarData(barDataSet)
                barChart.data = barData;
                barChart.invalidate()

                val xAxisLabel: ArrayList<String> = ArrayList()
                xAxisLabel.add("Chinese")
                xAxisLabel.add("Malay")
                xAxisLabel.add("India")
                xAxisLabel.add("International")
                xAxisLabel.add("Bangladesh")
                xAxisLabel.add("Myanmar")

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
            tabStudent().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}