package com.example.fitnessapp.foodAPI

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FoodActivity : AppCompatActivity() {

    // Haetaan RecyclerView-näkymä ja alustetaan lista
    lateinit var recyclerView: RecyclerView
    lateinit var list: ArrayList<FoodItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        // Haetaan viittaus RecyclerView-näkymään ja luodaan adapteri
        recyclerView=findViewById(R.id.rvFoodList)
        list = ArrayList()
        val adapter = RecyclerAdapter(list, this)
        recyclerView.adapter=adapter

        // Asetetaan LinearLayoutManager-näkymä RecyclerView:lle
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        // Luodaan HttpLoggingInterceptor ja OkHttpClient, jotta voidaan seurata verkkopyyntöjen logeja
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        // Luodaan Retrofit-olio, joka käyttää rajapintaa ApiInterface
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com/v1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api: ApiInterface =retrofit.create(ApiInterface::class.java)

        // Käytetään rajapintaa ApiInterfacen avulla ja tehdään verkkopyyntö "getData()"
        val call: Call<Food> = api.getData()
        call.enqueue(object: Callback<Food?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Food?>, response: Response<Food?>) {

                if (response.isSuccessful){
                    // Tyhjennetään listan sisältö ja lisätään uudet tiedot
                    list.clear()
                    for (myData in response.body()!!){

                        list.add(myData)
                    }
                    // Päivitetään adapteri uusilla tiedoilla
                    adapter.notifyDataSetChanged()

                }

            }
            // Näytetään Toast-viesti, jos verkkopyyntö epäonnistuu
            override fun onFailure(call: Call<Food?>, t: Throwable) {
                Toast.makeText(this@FoodActivity, "Callback error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}