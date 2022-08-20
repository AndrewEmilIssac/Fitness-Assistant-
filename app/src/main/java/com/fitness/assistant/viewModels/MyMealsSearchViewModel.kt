

package com.fitness.assistant.viewModels

import androidx.lifecycle.ViewModel
import com.fitness.assistant.data.repositories.FoodRepository
import javax.inject.Inject

class MyMealsSearchViewModel @Inject constructor(private val foodRepository: FoodRepository) :
    ViewModel() {

    val myProducts = foodRepository.myProducts

    fun updateProducts() =
        foodRepository.updateProducts()

}