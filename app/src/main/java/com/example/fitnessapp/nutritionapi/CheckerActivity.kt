package com.example.fitnessapp.nutritionapi

import android.os.Bundle
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessapp.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken

class CheckerActivity : AppCompatActivity() {

    lateinit var tvData: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checker)

        tvData = findViewById(R.id.tvData)

        // Create HttpLoggingInterceptor and OkHttpClient for logging network requests
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        // Create Retrofit instance using ApiInterface
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com/v1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api: ApiInterface = retrofit.create(ApiInterface::class.java)

        val searchView = findViewById<SearchView>(R.id.svCalories)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                fetchNutritionData(api, query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun fetchNutritionData(api: ApiInterface, query: String) {
        val call: Call<JsonArray> = api.getData(query)
        call.enqueue(object : Callback<JsonArray> {
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                if (response.isSuccessful) {
                    val jsonArray = response.body()
                    if (jsonArray != null) {
                        val gson = Gson()
                        val type = object : TypeToken<List<NutritionItem>>() {}.type
                        val nutritionItems = gson.fromJson<List<NutritionItem>>(jsonArray, type)

                        // Clear the TextView and append the data from the API response
                        tvData.text = ""
                        for (item in nutritionItems) {
                            tvData.append(item.toString())
                            tvData.append("\n\n")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                Toast.makeText(this@CheckerActivity, "Callback error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
