package com.example.fitnessapp.foodAPI

import com.example.fitnessapp.nutritionapi.Nutrition
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {
    @Headers("X-Api-Key: " + "+Lj7efTo0Vwdjbhj9H+OdQ==P6VzwW7KvI1VHqxt")
    @GET("recipe?query=Elegant Rice and Chicken")
    fun getData(): Call<Food>
}