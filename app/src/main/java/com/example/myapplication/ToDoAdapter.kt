package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.example_todo.view.*

class ToDoAdapter (private var toDoList: List<item>,
                   private val listener: onItemClickListener
):RecyclerView.Adapter<ToDoAdapter.toDoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): toDoHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.example_todo, parent, false)
        return toDoHolder(itemView)
    }

    override fun onBindViewHolder(holder: toDoHolder, position: Int) {
        val currentItem = toDoList[position]
        holder.tittle.text = currentItem.text
        holder.isDone.isChecked = currentItem.isChecked
    }

    override fun getItemCount() = toDoList.size

    inner class toDoHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var tittle = itemView.tvText
        var isDone = itemView.cbDone
        init {
            if(R.id.cbDone is)
            itemView.cbDone.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            if (position != Adapter.NO_SELECTION) {
                listener.onItemClick(position)
            }
        }

    }
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

}