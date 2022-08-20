package com.fitness.assistant.ui.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fitness.assistant.R

class DayItemViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    val textTitle: TextView = itemView.findViewById(R.id.day_item_holder_title)
    val textCalories: TextView = itemView.findViewById(R.id.day_item_holder_calories)

}