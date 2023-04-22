package com.example.fitnessapp.foodAPI

// FoodItem tietoluokka
data class FoodItem(
	val instructions: String,
	val servings: String,
	val ingredients: String,
	val title: String
)

{
	override fun toString(): String {
		return "Title: $title\n\n" +
				"Servings: $servings\n\n" +
				"Ingredients: $ingredients\n\n" +
				"Instructions: $instructions\n"
	}
}

