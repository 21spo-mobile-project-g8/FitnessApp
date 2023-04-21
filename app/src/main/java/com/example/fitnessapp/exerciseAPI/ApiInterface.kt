package com.example.fitnessapp.exerciseAPI

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {
    @Headers("X-Api-Key: " + "W3QAJsTRC9vBQ8Q4PGTi7wKEA4gQNHSOLcCmfDvu")
    @GET("exercises")
<<<<<<< Updated upstream:app/src/main/java/com/example/fitnessapp/api/ApiInterface.kt
    fun getData(@Query("muscle")muscle: String): Call<DataModel>
=======
    fun getData(@Query("muscle") muscle: String): Call<DataModel>
>>>>>>> Stashed changes:app/src/main/java/com/example/fitnessapp/exerciseAPI/ApiInterface.kt
}