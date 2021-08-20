package com.example.myapplication

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_to_do_list.*

class ToDoList : AppCompatActivity(), ToDoAdapter.onItemClickListener {
    private val dumpList = mutableListOf<item>()
    private val adapter = ToDoAdapter(dumpList, this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_list)
        rvToDos.adapter = adapter
        rvToDos.layoutManager = LinearLayoutManager(this)
        btnAddToDo.setOnClickListener {
                addItem()
        }
    }

    private fun addItem(){
        if (tvAddToDo.text.toString() != ""){
            val text = tvAddToDo.text.toString()
            tvAddToDo.text = null
            val item = item(text, false)
            dumpList.add(dumpList.size, item)
            adapter.notifyItemInserted(dumpList.size)
        } else {
            Toast.makeText(this, "You don't have an activity!!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClick(position: Int) {
        val deleteDialog = AlertDialog.Builder(this)
            .setTitle("Delete")
            .setMessage("Do you want to delete this activity?")
            .setPositiveButton("Yes"){ _: DialogInterface, _: Int ->
                removeItem(position)
                Toast.makeText(this, "You deleted activity", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No"){ _: DialogInterface, _: Int ->

            }.create()
        deleteDialog.show()

    }

    private fun removeItem(position: Int){
        dumpList.removeAt(position)
        adapter.notifyItemRemoved(position)
    }
}