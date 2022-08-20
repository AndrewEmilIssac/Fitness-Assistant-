

package com.fitness.assistant.data.databases.daos

import androidx.room.*
import com.fitness.assistant.data.models.FoodProduct
import com.fitness.assistant.util.FOOD_DATABASE_ENTRIES

@Dao
interface FoodProductDao {

    @Query("select * from food")
    fun getAllFood():List<FoodProduct>

    @Query("select * from food where name like '%' || :name || '%'")
    fun searchByName(name: String):List<FoodProduct>

    @Query("select * from food where id == :id")
    fun searchById(id: Int):FoodProduct?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(product: FoodProduct)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProducts(products: List<FoodProduct>)

    @Query("select rowid from food order by ROWID desc limit 1")
    fun getSize():Int

    @Delete
    fun deleteProduct(product: FoodProduct)

    @Query("select * from food where id > $FOOD_DATABASE_ENTRIES")
    fun getCustomProducts(): List<FoodProduct>

    @Query("delete from food where id > $FOOD_DATABASE_ENTRIES")
    fun deleteCustomProducts()

    @Query("select * from food where name == :name")
    fun searchByNameExact(name: String): FoodProduct

    @Query("select name from food where name like '%' || :suggestion || '%'")
    fun searchBySuggestion(suggestion: String): List<String>?

}