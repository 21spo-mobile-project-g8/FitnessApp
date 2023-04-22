package com.example.fitnessapp.foodAPI

import com.example.fitnessapp.nutritionapi.Nutrition
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

//Määritellään rajapintaluokka ApiInterface.
interface ApiInterface {

    // Määritellään pyynnön tiedot. Käytetään "X-Api-Key" -headeria, jonka arvona on API avain.
    @Headers("X-Api-Key: " + "+Lj7efTo0Vwdjbhj9H+OdQ==P6VzwW7KvI1VHqxt")

    // Määritellään HTTP GET -pyyntö
    @GET("recipe")

    // Määritellään rajapinnan funktio getData, joka palauttaa Call-tyyppisen objektin, joka sisältää Nutrition-tyyppisiä tietoja
    fun getData(@Query("query") query: String): Call<JsonArray>
}