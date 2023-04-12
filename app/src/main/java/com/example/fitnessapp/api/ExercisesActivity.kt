package com.example.fitnessapp.api

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.PopupMenu
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

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerAdapter
    private lateinit var list: ArrayList<DataModelItem>
    private lateinit var api: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        recyclerView = findViewById(R.id.rvExercisesList)

        list = ArrayList()

        adapter = RecyclerAdapter(list, this)

        recyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com/v1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ApiInterface::class.java)

        loadData("lats") // load data for the default muscle group

        // create a menu button in the toolbar
        val toolbar: Toolbar = findViewById(R.id.tbMenu)
        setSupportActionBar(toolbar)
        val menuButton: ImageButton = findViewById(R.id.buMenu)
        menuButton.setOnClickListener {
            showMenu(menuButton)
        }
    }

    private fun loadData(muscleGroup: String) {
        val call: Call<DataModel> = api.getData(muscleGroup)

        call.enqueue(object : Callback<DataModel?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<DataModel?>, response: Response<DataModel?>) {
                if (response.isSuccessful) {
                    list.clear()
                    response.body()?.let {
                        list.addAll(it)
                    }
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<DataModel?>, t: Throwable) {
                Toast.makeText(this@ExercisesActivity, "Callback error", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun showMenu(view: View) {
        val popup = PopupMenu(this, view)
        popup.menuInflater.inflate(R.menu.muscle_menu, popup.menu)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.abdominals_item -> {
                    loadData("chest")
                    true
                }
                R.id.abductors_item -> {
                    loadData("back")
                    true
                }
                R.id.adductors_item -> {
                    loadData("legs")
                    true
                }
                R.id.biceps_item -> {
                    loadData("shoulders")
                    true
                }
                R.id.calves_item -> {
                    loadData("shoulders")
                    true
                }
                R.id.chest_item -> {
                    loadData("shoulders")
                    true
                }
                R.id.forearms_item -> {
                    loadData("shoulders")
                    true
                }
                R.id.glutes_item -> {
                    loadData("shoulders")
                    true
                }
                R.id.hamstrings_item -> {
                    loadData("shoulders")
                    true
                }
                R.id.lats_item -> {
                    loadData("shoulders")
                    true
                }
                R.id.lower_back_item -> {
                    loadData("shoulders")
                    true
                }
                R.id.middle_back_item -> {
                    loadData("shoulders")
                    true
                }
                R.id.neck_item -> {
                    loadData("shoulders")
                    true
                }
                R.id.quadriceps_item -> {
                    loadData("shoulders")
                    true
                }
                R.id.traps_item -> {
                    loadData("shoulders")
                    true
                }
                R.id.triceps_item -> {
                    loadData("shoulders")
                    true
                }
                else -> false
            }
        }
        popup.show()
    }
}