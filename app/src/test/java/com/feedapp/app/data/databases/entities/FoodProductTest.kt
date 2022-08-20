

package com.fitness.assistant.data.databases.entities

import com.fitness.assistant.data.models.FoodProduct
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class FoodProductTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun createFoodProduct(){
        val name = "Tasty Cake"
        val energy = 1200F
        val id = 977
        val product = FoodProduct(
            id = id,
            name = name,
            energy = energy,
            calories = 0f,
            carbs = 0f,
            proteins = 0f,
            fats = 0f
        )
        assert(product.energy == energy)
        assert(product.id == id)
        assert(product.name == name)
    }

}