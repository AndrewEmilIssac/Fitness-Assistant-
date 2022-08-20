package com.fitness.assistant.data.modelsimport com.fitness.assistant.data.api.models.recipedetailed.nn.NutrientXimport com.fitness.assistant.data.api.models.recipedetailed.nn.Nutritionimport com.fitness.assistant.data.api.models.recipedetailed.nn.RecipeDetailedResponseimport org.junit.jupiter.api.Testinternal class ConverterToProductTest {    private val converter = ConverterToProduct()    companion object {        private const val grams = 100f    }    @Test    fun convertRecipe() {        fun oneServingTest() {            val proteinsAmount = 311.3f            val nutrients = arrayListOf(NutrientX(amount = proteinsAmount, title = "Protein"))            val nutrition = Nutrition(nutrients = nutrients)            val recipe = RecipeDetailedResponse(nutrition = nutrition)            val converted = converter.convertRecipe(recipe, 1)            assert(proteinsAmount == converted.consumedProtein)        }        fun threeServingsTest() {            val proteinsAmount = 311.3f            val nutrients = arrayListOf(NutrientX(amount = proteinsAmount, title = "Protein"))            val nutrition = Nutrition(nutrients = nutrients)            val recipe = RecipeDetailedResponse(nutrition = nutrition)            val converted = converter.convertRecipe(recipe, 3)            assert(proteinsAmount * 3 == converted.consumedProtein)        }        oneServingTest()        threeServingsTest()    }    @Test    fun convertFoodProduct() {        fun test100Grams() {            val proteinsAmount = 81f            val foodProduct = FoodProduct(id = 0, name = "Cake", proteins = proteinsAmount, calories = 0f, fats = 0f, carbs = 0f)            val converted = converter.convertFoodProduct(foodProduct, grams)            assert(proteinsAmount == converted.consumedProtein)        }        fun test350grams() {            val proteinsAmount = 81f            val grams = 350f            val foodProduct = FoodProduct(id = 0, name = "Cake", proteins = proteinsAmount, calories = 0f, fats = 0f, carbs = 0f)            val converted = converter.convertFoodProduct(foodProduct, grams)            assert(proteinsAmount * (grams / 100) == converted.consumedProtein)        }        test100Grams()        test350grams()    }}