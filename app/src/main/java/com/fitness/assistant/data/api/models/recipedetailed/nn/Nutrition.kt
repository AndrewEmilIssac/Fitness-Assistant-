

package com.fitness.assistant.data.api.models.recipedetailed.nn

import com.fitness.assistant.data.models.BasicNutrientType

class Nutrition(
    var ingredients: ArrayList<IngredientX> = arrayListOf(),
    var nutrients: ArrayList<NutrientX> = arrayListOf()
) {
    fun getAmountByNutrient(type: BasicNutrientType): Float {
        return when (type) {
            BasicNutrientType.PROTEINS -> {
                nutrients.find { it.title == "Protein" }?.amount ?: 0f
            }
            BasicNutrientType.FATS -> {
                nutrients.find { it.title == "Fat" }?.amount ?: 0f
            }
            BasicNutrientType.CARBS -> {
                nutrients.find { it.title == "Carbohydrates" }?.amount ?: 0f
            }
            else -> 0f
        }
    }
}