package com.example.fitnessapp.nutritionapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R

class RecyclerAdapter(val list:ArrayList<NutritionItem>, val context: Context):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val titleTxt: TextView =itemView.findViewById(R.id.nutritionTitle)

        val nutritionTxt: TextView =itemView.findViewById(R.id.nutritionInfo)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.nutrition_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem=list[position]


        holder.apply {
            titleTxt.text = currentItem.name

            nutritionTxt.text = currentItem.calories.toString() + " calories"
        }

    }
}