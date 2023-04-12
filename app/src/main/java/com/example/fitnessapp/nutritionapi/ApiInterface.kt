package com.example.fitnessapp.nutritionapi

import com.example.fitnessapp.api.DataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {
    @Headers("X-Api-Key: " + "cju/TWZ0lueDYBjujhtdfQ==FJLwtBt15QpYfTbL")
    @GET("nutrition?query=1lb brisket and fries")
    fun getData(): Call<Nutrition>
}