package com.fitness.assistant.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fitness.assistant.R
import com.fitness.assistant.data.api.models.recipedetailed.nn.Step
import com.fitness.assistant.ui.viewholders.RecipeStepViewHolder


class RecipeStepAdapter(private val steps: ArrayList<Step>) :
    RecyclerView.Adapter<RecipeStepViewHolder>() {

    override fun onBindViewHolder(holder: RecipeStepViewHolder, position: Int) {
        try {
            val step = steps[position]
            holder.textTitle.text = step.step
            holder.textNum.text = step.number.toString()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeStepViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.vh_recipes_step, parent, false)
        return RecipeStepViewHolder(view)
    }


    override fun getItemCount(): Int {
        return steps.size
    }

    fun updateList(newList: List<Step>) {
        steps.clear()
        steps.addAll(newList)
        notifyDataSetChanged()
    }

}







