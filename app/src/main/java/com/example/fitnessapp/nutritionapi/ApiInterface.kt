package com.example.fitnessapp.nutritionapi

import com.example.fitnessapp.api.DataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

//Määritellään rajapintaluokka ApiInterface.
interface ApiInterface {
    // Määritellään pyynnön tiedot. Käytetään "X-Api-Key" -headeria, jonka arvona on API avain.
    @Headers("X-Api-Key: " + "cju/TWZ0lueDYBjujhtdfQ==FJLwtBt15QpYfTbL")

    // Määritellään HTTP GET -pyyntö
    @GET("nutrition?query=100g chicken breast")

    // Määritellään rajapinnan funktio getData, joka palauttaa Call-tyyppisen objektin, joka sisältää Nutrition-tyyppisiä tietoja.
    fun getData(): Call<Nutrition>
}