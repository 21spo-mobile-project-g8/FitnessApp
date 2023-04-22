package com.example.fitnessapp

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class TrackerActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var etWeight: EditText
    private lateinit var etDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracker)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        etWeight = findViewById(R.id.etWeight)
        etDate = findViewById(R.id.etDate)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val chart = findViewById<LineChart>(R.id.chart)

        btnSave.setOnClickListener {
            val weight = etWeight.text.toString()
            val date = etDate.text.toString()

            if (weight.isNotEmpty() && date.isNotEmpty()) {
                saveWeightDataToFirestore(weight, date, chart) // Pass chart as a parameter
            } else {
                Toast.makeText(this, "Please enter weight and date", Toast.LENGTH_SHORT).show()
            }
        }

        fetchWeightDataFromFirestore(chart)
    }

    private fun saveWeightDataToFirestore(weight: String, date: String, chart: LineChart) { // Add chart as a parameter
        val user = auth.currentUser
        if (user != null) {
            val weightData = hashMapOf(
                "weight" to weight.toDouble(),
                "date" to date
            )

            db.collection("users")
                .document(user.uid)
                .collection("weights")
                .add(weightData)
                .addOnSuccessListener {
                    Toast.makeText(this, "Weight data saved", Toast.LENGTH_SHORT).show()
                    etWeight.text.clear()
                    etDate.text.clear()
                    fetchWeightDataFromFirestore(chart) // Add this line to update the chart
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show()
        }
    }



    private fun fetchWeightDataFromFirestore(chart: LineChart) {
        val user = auth.currentUser
        if (user != null) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            db.collection("users")
                .document(user.uid)
                .collection("weights")
                .orderBy("date")
                .get()
                .addOnSuccessListener { documents ->
                    val entries = mutableListOf<Entry>()
                    val dates = mutableListOf<String>()
                    var index = 0f
                    for (doc in documents) {
                        val weight = doc.getDouble("weight") ?: continue
                        val dateStr = doc.getString("date") ?: continue

                        entries.add(Entry(index++, weight.toFloat()))
                        dates.add(dateStr)
                    }
                    displayGraph(chart, entries, dates)
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun displayGraph(chart: LineChart, entries: List<Entry>, dates: List<String>) {
        val dataSet = LineDataSet(entries, "Weight")
        dataSet.color = Color.RED
        dataSet.lineWidth = 2f

        chart.data = LineData(dataSet)
        chart.description.isEnabled = false

        // Format X-axis to display dates instead of timestamps
        val xAxis = chart.xAxis
        xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return if (value.toInt() in dates.indices) {
                    dates[value.toInt()]
                } else {
                    ""
                }
            }
        }
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setLabelCount(5, true)
        xAxis.granularity = 1f

        chart.invalidate()
    }


}
