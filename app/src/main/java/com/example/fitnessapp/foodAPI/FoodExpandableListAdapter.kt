package com.example.fitnessapp.foodAPI

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.fitnessapp.R

class FoodExpandableListAdapter(
    private val context: Context,
    private val foodItems: List<FoodItem>
) : BaseExpandableListAdapter() {

    override fun getGroup(groupPosition: Int): FoodItem {
        return foodItems[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): String {
        val foodItem = foodItems[groupPosition]
        return "Servings: ${foodItem.servings}\n\n" +
                "Ingredients: ${foodItem.ingredients}\n\n" +
                "Instructions: ${foodItem.instructions}\n"
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return foodItems.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return 1
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(android.R.layout.simple_expandable_list_item_1, null)
        }
        val textView = view!!.findViewById<TextView>(android.R.id.text1)
        textView.text = getGroup(groupPosition).title
        return view
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(android.R.layout.simple_expandable_list_item_2, null)
        }
        val textView = view!!.findViewById<TextView>(android.R.id.text2)
        textView.text = getChild(groupPosition, childPosition)
        return view
    }
}
