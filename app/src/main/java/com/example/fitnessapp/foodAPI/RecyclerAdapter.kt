package com.example.fitnessapp.foodAPI

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R

    // Määritellään RecyclerAdapter-luokka, joka perii RecyclerView.Adapter-luokan ominaisuudet ja metodit.
    // Luokan konstruktori vaatii listan reseptistä.
    class RecyclerAdapter(val list:ArrayList<FoodItem>, val context: Context): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

        // ViewHolder-luokka sisältää näkymän reseptille.
        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

            // Alustetaan TextView-luokan ominaisuudet ViewHolder-luokan näkymässä.
            val titleTxt: TextView =itemView.findViewById(R.id.recipeTitle)
            val recipeTxt: TextView =itemView.findViewById(R.id.recipeInfo)
        }

        // Metodi, joka luo uuden ViewHolder-luokan näkymän.
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            // Luodaan näkymä recipe_item-tiedostosta.
            val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)

            // Palautetaan uusi ViewHolder-luokan näkymä.
            return ViewHolder(view)
        }

        // Metodi, joka palauttaa listan.
        override fun getItemCount(): Int {
            return list.size
        }

        // Metodi, joka asettaa reseptin tiedot ViewHolder-luokan näkymään tietyn sijainnin perusteella.
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val currentItem=list[position]

            // Asetetaan reseptin tiedot ViewHolder-luokan näkymään.
            holder.apply {
                titleTxt.text = currentItem.title

                recipeTxt.text = currentItem.ingredients.toString() + " recipes"
            }

        }
    }
