package com.example.fitnessapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {
    @Headers("X-Api-Key: " + "W3QAJsTRC9vBQ8Q4PGTi7wKEA4gQNHSOLcCmfDvu")
    @GET("exercises?muscle=biceps")
    fun getData(): Call<DataModel>
}