package com.example.fitnessapp.exerciseApi.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ExerciseService {

    companion object{
        private const val BASE_URL = "https://api.api-ninjas.com/v1/"
        private var retrofit : Retrofit? = null

        fun getInstance() : Retrofit{
            if (retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}