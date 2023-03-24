package com.example.fitnessapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class CaloriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calories)
        //access the items of the list
        val intensity = resources.getStringArray(R.array.intensity)

        //access the intensitySpinner
        val spinner = findViewById<Spinner>(R.id.intensitySpinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, intensity)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    //Toast what shows picked intensity
                    Toast.makeText(this@CaloriesActivity,
                        getString(R.string.selected_intensity) + " " +
                            "" + intensity[position], Toast.LENGTH_SHORT).show()
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }
}