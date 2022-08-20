package com.fitness.assistant.ui.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.fitness.assistant.R

class RecipeApiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textTitle: TextView = itemView.findViewById(R.id.vh_recipes_title)
    val mainLayout: CardView = itemView.findViewById(R.id.vh_recipes_mainLayout)
    val image: ImageView = itemView.findViewById(R.id.vh_recipes_image)

}