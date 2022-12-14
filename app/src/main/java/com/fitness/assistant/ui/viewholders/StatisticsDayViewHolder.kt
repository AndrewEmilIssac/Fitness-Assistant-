package com.fitness.assistant.ui.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fitness.assistant.R

class StatisticsDayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.vh_statistics_name)
    val image: ImageView = itemView.findViewById(R.id.vh_statistics_image)
}