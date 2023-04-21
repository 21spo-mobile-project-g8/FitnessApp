package com.example.fitnessapp


import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessapp.nutritionapi.CheckerActivity

class CaloriesHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calorieshome)

        var caloriesButton : Button = findViewById(R.id.caloriesButton)
        caloriesButton.setOnClickListener {
            val intent = Intent(this, CaloriesActivity::class.java).apply{

            }
            startActivity(intent);
        }

        var checkerButton : Button = findViewById(R.id.checkerButton)
        checkerButton.setOnClickListener {
            val intent = Intent(this, CheckerActivity::class.java).apply{

            }
            startActivity(intent);
        }
    }
}