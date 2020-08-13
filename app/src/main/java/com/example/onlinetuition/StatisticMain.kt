package com.example.onlinetuition

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener


class StatisticMain : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var statisticPager: ViewPager
    lateinit var pagerAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistic_main)
        setSupportActionBar(findViewById(R.id.toolbar_statistics))
        supportActionBar?.title = "Statistics"

        tabLayout = findViewById(R.id.tab_layout)
        statisticPager = findViewById(R.id.statistics_pager)
        pagerAdapter = PageAdapter(supportFragmentManager, tabLayout.tabCount)
        statisticPager.adapter = pagerAdapter

        tabLayout.setOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                statisticPager.currentItem = tab.position
                Log.println(Log.INFO, "debugtop", tab.position.toString())
                when (tab.position) {
                    0 -> {
                        pagerAdapter.notifyDataSetChanged();
                    }
                    1 -> {
                        pagerAdapter.notifyDataSetChanged();
                    }
                    2 -> {
                        pagerAdapter.notifyDataSetChanged();
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        statisticPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabLayout))

    }
}