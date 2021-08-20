package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val name = intent.getStringExtra("EXTRA_NAME")
        val age = intent.getIntExtra("EXTRA_AGE",0)
        val country = intent.getStringExtra("EXTRA_COUNTRY")
        tvShowInfo.text = "Hello ${name}, $age years old from $country"
        btnGoBack.setOnClickListener {
            finish()
        }

        btnGetPicture.setOnClickListener {
            openSomeActivityForResult()
        }
        val customList = listOf("First", "Second", "Third")
        val adapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, customList)
        spMonths.adapter = adapter
        spMonths.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@SecondActivity, "You selected ${adapterView?.getItemAtPosition(position)}", Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            //val data: Intent? = result.data
            val uri = result.data?.data
            ivPicture.setImageURI(uri)
        }
    }

    private fun openSomeActivityForResult() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).also {
            it.type = "image/*"
        }
        resultLauncher.launch(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater = menuInflater
        inflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId){
            R.id.miSetting -> {
                Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show()
                true
            }
            R.id.miFavorite -> {
                Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show()
                true
            }
            R.id.miClose -> {
                finish()
                true
            }
            R.id.miAddContact -> {
                Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show()
                true
            }
            R.id.miFeedback -> {
                Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show()
                true
            }
            else -> false
        }

    }
}