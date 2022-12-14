

package com.fitness.assistant.data.databases.dbclasses

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fitness.assistant.data.databases.converters.Converters
import com.fitness.assistant.data.databases.daos.DayDao
import com.fitness.assistant.data.databases.daos.RecentDao
import com.fitness.assistant.data.databases.daos.UserDao
import com.fitness.assistant.data.models.FoodProduct
import com.fitness.assistant.data.models.Product
import com.fitness.assistant.data.models.RecentProduct
import com.fitness.assistant.data.models.day.Day
import com.fitness.assistant.data.models.day.Meal
import com.fitness.assistant.data.models.user.User


@Database(
    entities = [User::class, Meal::class, FoodProduct::class, Product::class, Day::class, RecentProduct::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getDayDao(): DayDao
    abstract fun getRecentDao(): RecentDao

    companion object {
        @Volatile
        private var instance: UserDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                instance
                    ?: buildDatabase(
                        context
                    ).also { instance = it }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, UserDatabase::class.java, "user.db"
        )
            .build()

    }




}