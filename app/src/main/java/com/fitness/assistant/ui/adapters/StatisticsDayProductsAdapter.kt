

package com.fitness.assistant.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fitness.assistant.R
import com.fitness.assistant.data.models.Product
import com.fitness.assistant.ui.viewholders.StatisticsDayViewHolder


class StatisticsDayProductsAdapter(
    val products: ArrayList<Product>,
    private val deleter: ((Product) -> Unit)
) : RecyclerView.Adapter<StatisticsDayViewHolder>() {

    override fun onBindViewHolder(holder: StatisticsDayViewHolder, position: Int) {
        try {
            val product = products[position]
            holder.name.text = product.name
            holder.image.setOnClickListener {
                deleter.invoke(product)
                products.remove(product)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticsDayViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.vh_statistics_product, parent, false)
        return StatisticsDayViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun updateList(newList: List<Product>) {
        products.clear()
        products.addAll(newList)
        notifyDataSetChanged()
    }
}


