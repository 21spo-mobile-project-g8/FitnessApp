package com.example.fitnessapp.exerciseAPI

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R

class RecyclerAdapter(val list:ArrayList<DataModelItem>, val context: Context, private val muscle: String):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val titleTxt: TextView =itemView.findViewById(R.id.exerciseTitle)

        val muscleTxt: TextView =itemView.findViewById(R.id.exerciseMuscle)

        val exerciseImage: ImageView = itemView.findViewById(R.id.exercisePoster)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.exercise_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem=list[position]

        holder.apply {
            titleTxt.text = currentItem.name
            muscleTxt.text = currentItem.muscle
            exerciseImage.setImageResource(getMuscleImage(currentItem.muscle))
        }
    }

    private fun getMuscleImage(muscle: String): Int {
        return when (muscle) {
            "abdominals" -> R.drawable.abs
            "biceps" -> R.drawable.biceps
            "hamstrings" -> R.drawable.hamstrings
            "lats" -> R.drawable.lats
            "quadriceps" -> R.drawable.quads
            "traps" -> R.drawable.traps
            "triceps" -> R.drawable.triceps
            else -> R.drawable.error // Use a default image if muscle group is not recognized
        }
    }

}