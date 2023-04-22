package com.example.fitnessapp.exerciseAPI

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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
    private var muscle = "null"
    lateinit var recyclerView: RecyclerView
    lateinit var list: ArrayList<DataModelItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        val toolbar = findViewById<Toolbar>(R.id.tbExercise)
        setSupportActionBar(toolbar)

        recyclerView=findViewById(R.id.rvExercisesList)
        list = ArrayList()

        val adapter = RecyclerAdapter(list, this, muscle)
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
        val call: Call<DataModel> = api.getData(muscle)

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.exercise_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_abdominals -> {
                muscle = "abdominals"
                loadData()
                return true
            }
            R.id.menu_biceps -> {
                muscle = "biceps"
                loadData()
                return true
            }
            R.id.menu_chest -> {
                muscle = "chest"
                loadData()
                return true
            }
            R.id.menu_forearms -> {
                muscle = "forearms"
                loadData()
                return true
            }
            R.id.menu_glutes -> {
                muscle = "glutes"
                loadData()
                return true
            }
            R.id.menu_hamstrings -> {
                muscle = "hamstrings"
                loadData()
                return true
            }
            R.id.menu_lats -> {
                muscle = "lats"
                loadData()
                return true
            }
            R.id.menu_quadriceps -> {
                muscle = "quadriceps"
                loadData()
                return true
            }
            R.id.menu_traps -> {
                muscle = "traps"
                loadData()
                return true
            }
            R.id.menu_triceps -> {
                muscle = "triceps"
                loadData()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun loadData() {
        val api:ApiInterface = Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com/v1/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val call: Call<DataModel> = api.getData(muscle)

        call.enqueue(object: Callback<DataModel?>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<DataModel?>, response: Response<DataModel?>) {
                if (response.isSuccessful){
                    list.clear()
                    for (myData in response.body()!!){
                        list.add(myData)
                    }
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<DataModel?>, t: Throwable) {
                Toast.makeText(this@ExercisesActivity, "Callback error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}