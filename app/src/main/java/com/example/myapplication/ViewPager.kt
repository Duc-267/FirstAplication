package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_pager.*

class ViewPager : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        val textList = listOf(
            "Test 1",
            "Test 2",
            "Test 3",
            "Test 4",
            "Test 5",
            "Test 6",
            "Test 7"
        )
        val adapter = ViewPagerAdapter(textList)
        viewPager.adapter = adapter
    }
}