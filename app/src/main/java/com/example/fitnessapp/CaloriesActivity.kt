package com.example.fitnessapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.textfield.TextInputLayout


class CaloriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calories)

        val intensity = resources.getStringArray(R.array.intensity)
        val spinner = findViewById<Spinner>(R.id.intensitySpinner)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val ageInputLayout = findViewById<TextInputLayout>(R.id.etEnterYourAge)
        val ageInput = ageInputLayout.editText!!
        val heightInputLayout = findViewById<TextInputLayout>(R.id.etEnterYourHeight)
        val heightInput = heightInputLayout.editText!!
        val weightInputLayout = findViewById<TextInputLayout>(R.id.etEnterYourWeight)
        val weightInput = weightInputLayout.editText!!

        val genderRadioGroup = findViewById<RadioGroup>(R.id.genderRadioGroup)
        val consumptionTextView = findViewById<TextView>(R.id.consumptionTextView)

        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, intensity
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    // Show the selected intensity
                    Toast.makeText(
                        this@CaloriesActivity,
                        getString(R.string.selected_intensity) + " " +
                                "" + intensity[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Perform some action if needed
                }
            }
        }

        calculateButton.setOnClickListener {
            val age = ageInput.text.toString().toDoubleOrNull()
            val height = heightInput.text.toString().toDoubleOrNull()
            val weight = weightInput.text.toString().toDoubleOrNull()
            val gender = when (genderRadioGroup.checkedRadioButtonId) {
                R.id.manButton -> "male"
                R.id.womanButton -> "female"
                else -> null
            }
            val activityLevel = spinner.selectedItemPosition

            if (age != null && height != null && weight != null && gender != null) {
                val bmr = calculateBMR(age, height, weight, gender)
                val tdee = calculateTDEE(bmr, activityLevel)
                consumptionTextView.text = "Daily Caloric Needs: ${tdee.toInt()} kcal"
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculateBMR(age: Double, height: Double, weight: Double, gender: String): Double {
        return when (gender) {
            "male" -> (10 * weight) + (6.25 * height) - (5 * age) + 5
            "female" -> (10 * weight) + (6.25 * height) - (5 * age) - 161
            else -> 0.0
        }
    }

    private fun calculateTDEE(bmr: Double, activityLevel: Int): Double {
        val multiplier = when (activityLevel) {
            0 -> 1.2
            1 -> 1.375
            2 -> 1.55
            3 -> 1.725
            4 -> 1.9
            else -> 1.0
        }
        return bmr * multiplier
    }
}
