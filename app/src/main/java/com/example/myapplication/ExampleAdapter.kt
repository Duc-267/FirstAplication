package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.example_item.view.*

class ExampleAdapter(private val exampleList: List <ExampleItem>,
                     private val listener: OnItemClickListener
): RecyclerView.Adapter<ExampleAdapter.ExampleHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.example_item, parent, false)
        return ExampleHolder(itemView)
    }


    override fun onBindViewHolder(holder: ExampleHolder, position: Int) {
        val currentItem = exampleList[position]
        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2
    }

    override fun getItemCount() = exampleList.size

    inner class ExampleHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val imageView = itemView.imageView
        val textView1 = itemView.text_view1
        val textView2 = itemView.text_view2
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

    }
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}