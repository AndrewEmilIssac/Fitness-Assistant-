package com.fitness.assistant.data.models.calculationsimport com.fitness.assistant.data.models.BasicNutrientTypeimport com.fitness.assistant.data.models.day.MealTypeimport com.fitness.assistant.data.models.user.Userimport java.util.*import kotlin.math.roundToIntclass RecipesDetailsCalculator {    // get percentage of daily need of specific nutrient for user    fun getDailyPercentage(type: BasicNutrientType, user: User, amount: Float?): Pair<Int, Int> {        user.let {            var result: Float            result = amount ?: 0f            when (type) {                BasicNutrientType.CARBS ->                    result /= it.carbsNeeded.toFloat()                BasicNutrientType.PROTEINS ->                    result /= it.proteinsNeeded.toFloat()                BasicNutrientType.FATS ->                    result /= it.fatsNeeded.toFloat()                BasicNutrientType.CALORIES ->                    result = 0f            }            // get in percents            result *= 100            // percentage can be above 100            val percentage = result            if (result > 100f) result = 100f            return Pair(result.roundToInt(), percentage.roundToInt())        }    }    fun getDropDownInitialText(mealTypePosition: Int?): String {        return MealType.values()[mealTypePosition ?: 0].toString()    }    // set dropdown item according to current time    fun getMealTypeByCurrentTime(): MealType {        return when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {            in 0..10 -> MealType.BREAKFAST            in 11..14 -> MealType.LUNCH            in 15..17 -> MealType.SNACK            in 18..24 -> MealType.DINNER            else -> MealType.BREAKFAST        }    }    fun checkServingsAmount(servings: String): Boolean {        // accept only decimal        fun onlyDigits(servings: String): Boolean {            var onlyDigits = true            for (i in servings.indices) {                if (!Character.isDigit(servings[i])) {                    onlyDigits = false                    break                }            }            return onlyDigits        }        // check if it's not 0 servings        fun isOnlyZero(servings: String): Boolean {            if ((servings.toIntOrNull() ?: 1) == 0) return true            return false        }        return onlyDigits(servings) && !isOnlyZero(servings)    }}