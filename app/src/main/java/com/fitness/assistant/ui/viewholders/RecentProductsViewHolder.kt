package com.fitness.assistant.ui.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fitness.assistant.R

class RecentProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textTitle: TextView = itemView.findViewById(R.id.meals_card_name)
    val mainLayout: RelativeLayout = itemView.findViewById(R.id.mymeals_search_main)
    val image: ImageView = itemView.findViewById(R.id.mymeals_search_image)
    val imageLetter: TextView = itemView.findViewById(R.id.mymeals_search_letter)

}
