package com.fitness.assistant.ui.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fitness.assistant.R

class MyProductsRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textName: TextView = itemView.findViewById(R.id.meals_card_name)
    val imageLetter: TextView = itemView.findViewById(R.id.mymeals_search_letter)
    val textCalories: TextView = itemView.findViewById(R.id.meals_card_calories)
    val image: ImageView = itemView.findViewById(R.id.mymeals_search_image)


}