package com.example.fitnessapp.exerciseApi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.exerciseApi.services.ExerciseInterface
import com.example.fitnessapp.exerciseApi.services.ExerciseService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExerciseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        val rvExercisesList: RecyclerView = findViewById(R.id.rvExercisesList)

        rvExercisesList.layoutManager = LinearLayoutManager(this)
        rvExercisesList.adapter = ExerciseAdapter(emptyList())
        rvExercisesList.setHasFixedSize(true)

        getExerciseData { exercises: List<Exercise> ->
            rvExercisesList.adapter = ExerciseAdapter(exercises)
            rvExercisesList.adapter?.notifyDataSetChanged()
        }
    }

    private fun getExerciseData(callback: (List<Exercise>) -> Unit){
        val apiService = ExerciseService.getInstance().create(ExerciseInterface::class.java)
        apiService.getExerciseList().enqueue(object : Callback<ExerciseResponse> {
            override fun onResponse(
                call: Call<ExerciseResponse>,
                response: Response<ExerciseResponse>
            ) {
                if (response.isSuccessful){
                    val exercises = response.body()?.exercises ?: emptyList()
                    callback(exercises)
                }else{

            }}

            override fun onFailure(call: Call<ExerciseResponse>, t: Throwable) {

            }

        })
    }
}