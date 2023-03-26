package com.example.fitnessapp.exerciseApi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.exerciseApi.Exercise
import com.example.fitnessapp.databinding.ExerciseItemBinding


class ExerciseAdapter(
    private val exercises : List<Exercise>
) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>(){

    inner class ExerciseViewHolder(private val binding: ExerciseItemBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bindExercise(exercise : Exercise){
            binding.exerciseTitle.text = exercise.name
            binding.exerciseMuscle.text = exercise.muscle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding = ExerciseItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ExerciseViewHolder(binding)
    }

    override fun getItemCount(): Int = exercises.size

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bindExercise(exercises[position])
    }
}