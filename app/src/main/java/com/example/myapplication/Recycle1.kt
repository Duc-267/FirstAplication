package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recycle1.*
import kotlin.random.Random

class Recycle1 : AppCompatActivity(), ExampleAdapter.OnItemClickListener {
    private val exampleList = generateExampleList(100)
    private val adapter = ExampleAdapter(exampleList, this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle1)


        recycle_view.adapter = adapter
        recycle_view.layoutManager = LinearLayoutManager(this)
        btnAddItem.setOnClickListener {
            addRandomItem()
        }
        btnRemoveItem.setOnClickListener {
            removeRandomItem()
        }
    }

    private fun generateExampleList(size: Int): MutableList<ExampleItem>{
        val list = mutableListOf<ExampleItem>()
        for (i in 0 until size){
            val getIcon = when(i % 3){
                0 -> R.drawable.ic_add_contact_foreground
                1 -> R.drawable.ic_favorite_foreground
                else -> R.drawable.ic_setting_foreground
            }
            val item = ExampleItem(getIcon, "Item $i", "line 2")
            list += item
        }
        return list
    }
    private fun addRandomItem(){
        val position = Random.nextInt(6)
        val icon = R.drawable.ic_launcher_foreground
        val item = ExampleItem(icon, "Added Item", "line2")
        exampleList.add(position, item)
        adapter.notifyItemInserted(position)
    }
    private fun removeRandomItem(){
        val position = Random.nextInt(6)
        exampleList.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "item $position clicked", Toast.LENGTH_SHORT).show()
    }
}