package com.example.fitnessapp.foodAPI

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R



    class RecyclerAdapter(val list:ArrayList<FoodItem>, val context: Context): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

            val titleTxt: TextView =itemView.findViewById(R.id.recipeTitle)

            val instructionsTxt: TextView =itemView.findViewById(R.id.instructionsInfo)

            val servingsTxt: TextView =itemView.findViewById(R.id.servingsInfo)

            val ingredientsTxt: TextView =itemView.findViewById(R.id.ingredientsInfo)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)

            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return list.size
        }


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val currentItem=list[position]


            holder.apply {
                titleTxt.text = currentItem.title

                instructionsTxt.text = currentItem.instructions.toString() + " recipes"

                servingsTxt.text = currentItem.servings.toString()

                ingredientsTxt.text = currentItem.ingredients.toString()
            }

        }
    }
