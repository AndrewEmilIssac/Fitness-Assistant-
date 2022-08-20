package com.fitness.assistant.ui.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fitness.assistant.R

class RecipeStepViewHolder(
    itemView: View
) :
    RecyclerView.ViewHolder(itemView) {
    val textTitle: TextView = itemView.findViewById(R.id.vh_step_name)
    val textNum: TextView = itemView.findViewById(R.id.vh_step_num)


}
