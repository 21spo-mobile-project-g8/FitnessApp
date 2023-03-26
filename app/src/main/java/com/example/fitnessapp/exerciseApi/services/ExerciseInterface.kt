package com.example.fitnessapp.exerciseApi.services

import com.example.fitnessapp.exerciseApi.ExerciseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ExerciseInterface {
    @Headers("X-Api-Key: " + "RVVflRDUnNdin0s8fSQV6A==ql7aauH7LqJ8yOdk")
    @GET("exercises?muscle=biceps")
    fun getExerciseList(): Call<ExerciseResponse>

}