

package com.fitness.assistant.ui.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fitness.assistant.R

class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textTitle: TextView = itemView.findViewById(R.id.meal_api_title)
    val mainLayout: RelativeLayout = itemView.findViewById(R.id.meal_api_main)
    val image: ImageView = itemView.findViewById(R.id.meal_api_image)
    val imageLetter: TextView = itemView.findViewById(R.id.meal_api_image_letter)

}
