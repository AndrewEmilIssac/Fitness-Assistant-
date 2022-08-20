

package com.fitness.assistant.di.modules

import com.fitness.assistant.data.databases.daos.FoodProductDao
import com.fitness.assistant.data.repositories.SearchFoodRepository
import dagger.Module
import dagger.Provides


@Module
class ProductSearchModule {

    @Provides
    fun provideSearchFoodRepository(
        foodProductDao: FoodProductDao
    ): SearchFoodRepository {
        return SearchFoodRepository(foodProductDao)
    }


}
