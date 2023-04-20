package com.example.fitnessapp.nutritionapi

// NutritionItem tietoluokka
data class NutritionItem(
    val name: String,
    val query: String,
    val calories: Double,
    val serving_size_g: Int,
    val fat_total_g: Double,
    val fat_saturated_g: Int,
    val protein_g: Int,
    val carbohydrates_total_g: Int,
    val sugar_g: Int
)