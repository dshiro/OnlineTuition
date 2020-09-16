package com.example.onlinetuition

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.onlinetuition.databinding.FragmentTabRatingBinding
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

        val fragmentTabRatingBinding =  DataBindingUtil.inflate<FragmentTabRatingBinding>(inflater, R.layout.fragment_tab_rating, container, false)
        val view = fragmentTabRatingBinding.root

        val firebaseDB = FirebaseDatabase.getInstance().reference.child("User").child("Student")
        firebaseDB.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var arrRating = ArrayList<String>()

                for (postSnapshot in dataSnapshot.children) {
                    //Log.println(Log.INFO, "debug_rating", postSnapshot.toString())
                    arrRating.add(postSnapshot.child("rating").value.toString())
                }

                val total:Double = dataSnapshot.childrenCount.toDouble()
                val rate0 = arrRating.count { it == "0" }
                val rate1 = arrRating.count { it == "1" }
                val rate2 = arrRating.count { it == "2" }
                val rate3 = arrRating.count { it == "3" }
                val rate4 = arrRating.count { it == "4" }
                val rate5 = arrRating.count { it == "5" }

                val rate0_per = String.format("%.2f", (rate0 / total) * 100) + "%"
                val rate1_per = String.format("%.2f", (rate1 / total) * 100) + "%"
                val rate2_per = String.format("%.2f", (rate2 / total) * 100) + "%"
                val rate3_per = String.format("%.2f", (rate3 / total) * 100) + "%"
                val rate4_per = String.format("%.2f", (rate4 / total) * 100) + "%"
                val rate5_per = String.format("%.2f", (rate5 / total) * 100) + "%"

                val barEntry = ArrayList<BarEntry>();
                barEntry.add(BarEntry(0f, rate0.toFloat()));
                barEntry.add(BarEntry(1f, rate1.toFloat()));
                barEntry.add(BarEntry(2f, rate2.toFloat()));
                barEntry.add(BarEntry(3f, rate3.toFloat()));
                barEntry.add(BarEntry(4f, rate4.toFloat()));
                barEntry.add(BarEntry(5f, rate5.toFloat()));

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

                fragmentTabRatingBinding.ratingData =
                    RatingData(
                        rate1.toString(), rate1_per,
                        rate2.toString(), rate2_per,
                        rate3.toString(), rate3_per,
                        rate4.toString(), rate4_per,
                        rate5.toString(), rate5_per,
                        rate0.toString(), rate0_per
                    );
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