package com.example.fitnessapp.nutritionapi

data class NutritionItem(
    val name: String,
    val query: String,
    val calories: Double,
    val roundoff: String = String.format("%2f", calories)
    /*val carbohydrates_total_g: Int,
    val cholesterol_mg: Int,
    val fat_saturated_g: Int,
    val fat_total_g: Double,
    val fiber_g: Int,
    val potassium_mg: Int,
    val protein_g: Int,
    val serving_size_g: Int,
    val sodium_mg: Int,
    val sugar_g: Int*/
)