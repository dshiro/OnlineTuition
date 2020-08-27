package com.example.onlinetuition

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class tabRating : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_tab_student, container, false)

        val firebaseDB = FirebaseDatabase.getInstance().reference.child("User").child("Student")
        firebaseDB.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var arrRating = ArrayList<String>()

                for (postSnapshot in dataSnapshot.children) {
                    Log.println(Log.INFO, "debug_rating", postSnapshot.toString())
                    arrRating.add(postSnapshot.child("rating").value.toString())
                }

                val rate1 = arrRating.count { it == "1" }
                val rate2 = arrRating.count { it == "2" }
                val rate3 = arrRating.count { it == "3" }
                val rate4 = arrRating.count { it == "4" }
                val rate5 = arrRating.count { it == "5" }

                val barEntry = ArrayList<BarEntry>();
                barEntry.add(BarEntry(0f, rate1.toFloat()));
                barEntry.add(BarEntry(1f, rate2.toFloat()));
                barEntry.add(BarEntry(2f, rate3.toFloat()));
                barEntry.add(BarEntry(3f, rate4.toFloat()));
                barEntry.add(BarEntry(4f, rate5.toFloat()));

                val barChart: BarChart = view.findViewById(R.id.bar_chart);
                val barDataSet = BarDataSet(barEntry, "Rating");
                val barData = BarData(barDataSet)
                barChart.data = barData;
                barChart.invalidate()

                val xAxisLabel: ArrayList<String> = ArrayList()
                xAxisLabel.add("1")
                xAxisLabel.add("2")
                xAxisLabel.add("3")
                xAxisLabel.add("4")
                xAxisLabel.add("5")

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
            tabRating().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}