package com.example.fitnessapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.fitnessapp.exerciseAPI.ExercisesActivity
import com.example.fitnessapp.foodAPI.FoodActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var settingsButton : Button = findViewById(R.id.settingsButton)
        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java).apply{

            }
            startActivity(intent);
        }

        var foodButton : Button = findViewById(R.id.foodButton)
        foodButton.setOnClickListener {
            val intent = Intent(this, FoodActivity::class.java).apply{

            }
            startActivity(intent);
        }

        var exerciseButton : Button = findViewById(R.id.exerciseButton)
        exerciseButton.setOnClickListener {
            val intent = Intent(this, ExercisesActivity::class.java).apply{

            }
            startActivity(intent);
        }

        var calorieshomeButton : Button = findViewById(R.id.caloriesButton)
        calorieshomeButton.setOnClickListener {
            val intent = Intent(this, CaloriesHomeActivity::class.java).apply{

            }
            startActivity(intent);
        }

        var stepsButton : Button = findViewById(R.id.stepsButton)
        stepsButton.setOnClickListener {
            val intent = Intent(this, StepsActivity::class.java).apply{

            }
            startActivity(intent);
        }

        var trackerButton : Button = findViewById(R.id.trackerButton)
        trackerButton.setOnClickListener {
            val intent = Intent(this, TrackerActivity::class.java).apply{

            }
            startActivity(intent);
        }
    }
}

