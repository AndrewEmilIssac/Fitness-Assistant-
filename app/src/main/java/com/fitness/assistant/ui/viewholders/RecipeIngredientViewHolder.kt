package com.fitness.assistant.ui.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fitness.assistant.R

class RecipeIngredientViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textAmount: TextView = itemView.findViewById(R.id.vh_ing_amount)
    val textTitle: TextView = itemView.findViewById(R.id.vh_ing_name)
    val image: ImageView = itemView.findViewById(R.id.vh_recipes_ing_image)
}