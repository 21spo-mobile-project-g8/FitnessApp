package com.example.fitnessapp.nutritionapi

import com.google.gson.annotations.SerializedName

data class NutritionItem (
    val name: String,
    val calories: Int,
    val serving_size_g: Int,
    val fat_total_g: Float,
    val fat_saturated_g: Float,
    val protein_g: Float,
    val carbohydrates_total_g: Float,
    val sugar_g: Float
) {
    override fun toString(): String {
        return "Name: $name\n" +
                "Calories: $calories kcal\n" +
                "Serving size: $serving_size_g g\n" +
                "Total fat: $fat_total_g g\n" +
                "Saturated fat: $fat_saturated_g g\n" +
                "Protein: $protein_g g\n" +
                "Carbohydrates: $carbohydrates_total_g g\n" +
                "Sugar: $sugar_g g"
    }
}
