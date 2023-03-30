package com.example.fitnessapp.api

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



class ExercisesActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    lateinit var list: ArrayList<DataModelItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        recyclerView=findViewById(R.id.rvExercisesList)

        list = ArrayList()

        val adapter = RecyclerAdapter(list, this)

        recyclerView.adapter=adapter

        val layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit:Retrofit= Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com/v1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api:ApiInterface=retrofit.create(ApiInterface::class.java)

        val call: Call<DataModel> = api.getData()

        call.enqueue(object: Callback<DataModel?>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<DataModel?>, response: Response<DataModel?>) {

                if (response.isSuccessful){
                    list.clear()
                    for (myData in response.body()!!){

                        list.add(myData)
                    }

                    adapter.notifyDataSetChanged()



                }




            }

            override fun onFailure(call: Call<DataModel?>, t: Throwable) {

                Toast.makeText(this@ExercisesActivity, "Callback error", Toast.LENGTH_SHORT).show()
            }

        })





    }
}