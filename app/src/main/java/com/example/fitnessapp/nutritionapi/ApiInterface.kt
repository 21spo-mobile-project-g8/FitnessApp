package com.example.fitnessapp.nutritionapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import com.google.gson.JsonArray

//Määritellään rajapintaluokka ApiInterface.
interface ApiInterface {
    // Määritellään pyynnön tiedot. Käytetään "X-Api-Key" -headeria, jonka arvona on API avain.
    @Headers("X-Api-Key: " + "cju/TWZ0lueDYBjujhtdfQ==FJLwtBt15QpYfTbL")

    // Määritellään HTTP GET -pyyntö
    @GET("nutrition")

    // Määritellään rajapinnan funktio getData, joka palauttaa Call-tyyppisen objektin, joka sisältää Nutrition-tyyppisiä tietoja.
    fun getData(@Query("query") query: String): Call<JsonArray>
}