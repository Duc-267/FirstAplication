package com.example.myapplication

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnGoToSecond.setOnClickListener {
            requestPermission()
            val name = etFName.text.toString()
            val age = etAge.text.toString().toInt()
            val country = etCountry.text.toString()
            Intent(this, SecondActivity::class.java).also {
                it.putExtra("EXTRA_NAME", name)
                it.putExtra("EXTRA_AGE", age)
                it.putExtra("EXTRA_COUNTRY", country)
                startActivity(it)
            }
            Toast.makeText(this, "submitted", Toast.LENGTH_SHORT).show()
        }
        val addContactDialog = AlertDialog.Builder(this)
            .setTitle("Add Contact")
            .setIcon(R.drawable.ic_add_contact_foreground)
            .setPositiveButton("Yes"){ _: DialogInterface, _: Int ->
                Toast.makeText(this, "You added to your contact", Toast.LENGTH_LONG).show()
            }
            .setNegativeButton("No"){ _: DialogInterface, _: Int ->
                Toast.makeText(this, "You didn't add to your contact", Toast.LENGTH_LONG).show()
            }.create()
        btnDialog1.setOnClickListener {
            val name = etFName.text.toString()
            addContactDialog.setMessage("Do you want to add Mr.$name to your contact?")
            addContactDialog.show()
        }
        val options = arrayOf("First", "Second", "Third")
        val singleChoiceDialog = AlertDialog.Builder(this)
            .setTitle("Chose one of these option")
            .setSingleChoiceItems(options,0){ _: DialogInterface, i: Int ->
                Toast.makeText(this, "You chose ${options[i]}", Toast.LENGTH_LONG).show()
            }
            .setPositiveButton("Accept"){ _: DialogInterface, _: Int ->
                Toast.makeText(this, "You accepted singleDialog", Toast.LENGTH_LONG).show()
            }
            .setNegativeButton("Decline"){ _: DialogInterface, _: Int ->
                Toast.makeText(this, "You declined singleDialog", Toast.LENGTH_LONG).show()
            }.create()
        btnDialog2.setOnClickListener {
            singleChoiceDialog.show()
        }
        val multiChoiceDialog = AlertDialog.Builder(this)
            .setTitle("Chose one of these option")
            .setMultiChoiceItems(options, booleanArrayOf(true, true, true)){ _: DialogInterface, i: Int, isCheck: Boolean ->
                if(isCheck){
                    Toast.makeText(this, "You checked ${options[i]}", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "You unchecked ${options[i]}", Toast.LENGTH_LONG).show()
                }
            }
            .setPositiveButton("Accept"){ _: DialogInterface, _: Int ->
                Toast.makeText(this, "You accepted multiDialog", Toast.LENGTH_LONG).show()
            }
            .setNegativeButton("Decline"){ _: DialogInterface, _: Int ->
                Toast.makeText(this, "You declined multiDialog", Toast.LENGTH_LONG).show()
            }.create()
        btnDialog3.setOnClickListener {
            multiChoiceDialog.show()
        }
        btnRecycle1.setOnClickListener {
            Intent(this, Recycle1::class.java).also {
                startActivity(it)
            }
        }
        btnToDo.setOnClickListener {
            Intent(this, ToDoList::class.java).also {
                startActivity(it)
            }
        }
        btnGoToFragment.setOnClickListener {
            Intent(this, FragmentActivity::class.java).also{
                startActivity(it)
            }
        }
        btnGoToBottomNavigation.setOnClickListener {
            Intent(this, NavigationActivity::class.java).also{
                startActivity(it)
            }
        }
        btnGoToViewPager.setOnClickListener{
            Intent(this, ViewPager::class.java).also{
                startActivity(it)
            }
        }
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when (it.itemId){
                R.id.navItem1 ->{
                    Toast.makeText(this, "Item 1", Toast.LENGTH_SHORT).show()
                }
                R.id.navItem2 ->{
                    Toast.makeText(this, "Item 2", Toast.LENGTH_SHORT).show()
                }
                R.id.navItem3 ->{
                    Toast.makeText(this, "Item 3", Toast.LENGTH_SHORT).show()
                }
            }
            true

        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

//    override fun onContextItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.miAddContact -> Toast.makeText(this,"Added Contact", Toast.LENGTH_LONG ).show()
//            R.id.miClose -> finish()
//            R.id.miFavorite -> Toast.makeText(this,"Added Favorite", Toast.LENGTH_LONG ).show()
//            R.id.miSetting -> Toast.makeText(this, "Setting on", Toast.LENGTH_SHORT).show()
//        }
//        return true
//    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        // Handle item selection
        return when (item.itemId) {
            R.id.miAddContact -> {
                Toast.makeText(this,"Added Contact", Toast.LENGTH_LONG ).show()
                true
            }
            R.id.miClose -> {
                finish()
                true
            }
            R.id.miFavorite -> {
                Toast.makeText(this,"Added Favorite", Toast.LENGTH_LONG ).show()
                true
            }
            R.id.miSetting -> {
                Toast.makeText(this, "Setting on", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun hasWriteExternalStoragePermission():Boolean{
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    }



    private fun hasLocationForegroundPermission():Boolean{
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }
    private fun requestPermission(){
        val permissionToRequest = mutableListOf<String>()
        if(!hasWriteExternalStoragePermission()){
            permissionToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if(!hasLocationForegroundPermission()){
            permissionToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if(permissionToRequest.isNotEmpty()){
            ActivityCompat.requestPermissions(this, permissionToRequest.toTypedArray(),0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 0 && grantResults.isNotEmpty()){
            for (i in grantResults.indices){
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Log.d("RequestPermission", "${permissions[i]} is granted")
                }
            }
        }
    }
}