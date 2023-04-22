package com.example.fitnessapp.nutritionapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R

// Määritellään RecyclerAdapter-luokka, joka perii RecyclerView.Adapter-luokan ominaisuudet ja metodit.
// Luokan konstruktori vaatii listan ravintoaineista ja kontekstin.
class RecyclerAdapter(val list:ArrayList<NutritionItem>, val context: Context):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    // ViewHolder-luokka sisältää näkymän jokaiselle ravintoaineelle.
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        // Alustetaan TextView-luokan ominaisuudet ViewHolder-luokan näkymässä.
        val titleTxt: TextView =itemView.findViewById(R.id.nutritionTitle)
        val caloriesTxt: TextView =itemView.findViewById(R.id.nutritionCalories)
        val servingSizeTxt: TextView =itemView.findViewById(R.id.nutritionServingSize)
        val fatTotalTxt: TextView =itemView.findViewById(R.id.nutritionFatTotal)
        val fatSaturatedTxt: TextView =itemView.findViewById(R.id.nutritionFatSaturated)
        val proteinTxt: TextView =itemView.findViewById(R.id.nutritionProtein)
        val carbohydratesTxt: TextView =itemView.findViewById(R.id.nutritionCarbohydrates)
        val sugarTxt: TextView =itemView.findViewById(R.id.nutritionSugar)
    }

    // Metodi, joka luo uuden ViewHolder-luokan näkymän.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Luodaan näkymä nutrition_item-tiedostosta.
        val view = LayoutInflater.from(parent.context).inflate(R.layout.nutrition_item, parent, false)

        // Palautetaan uusi ViewHolder-luokan näkymä.
        return ViewHolder(view)
    }

    // Metodi, joka palauttaa listan ravintoaineiden kokonaismäärän.
    override fun getItemCount(): Int {
        return list.size
    }

    // Metodi, joka asettaa ravintoaineen tiedot ViewHolder-luokan näkymään tietyn sijainnin perusteella.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem=list[position]

    // Asetetaan ravintoaineen tiedot ViewHolder-luokan näkymään.
        holder.apply {
            titleTxt.text = "Name: " + currentItem.name
            caloriesTxt.text = "Calories: " + currentItem.calories.toString() + " kcal"
            servingSizeTxt.text = "Serving size: " + currentItem.serving_size_g.toString() + "g"
            fatTotalTxt.text = "Total fat: " + currentItem.fat_total_g.toString() + "g"
            fatSaturatedTxt.text = "Saturated fat: " + currentItem.fat_saturated_g.toString() + "g"
            proteinTxt.text = "Protein: " + currentItem.protein_g.toString() + "g"
            carbohydratesTxt.text = "Carbohydrates: " + currentItem.carbohydrates_total_g.toString() + "g"
            sugarTxt.text = "Sugar: " + currentItem.sugar_g.toString() + "g"
        }
    }
}