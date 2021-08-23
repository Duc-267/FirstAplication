package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view_pager.view.*

class ViewPagerAdapter(
    val textList: List<String>
):RecyclerView.Adapter<ViewPagerAdapter.viewPagerHolder>(){
    inner class viewPagerHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var textView = itemView.tvViewPagerText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewPagerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_pager, parent, false)
        return viewPagerHolder(view)
    }

    override fun onBindViewHolder(holder: viewPagerHolder, position: Int) {
        val current = textList[position]
        holder.textView.text = current
    }

    override fun getItemCount() = textList.size
}