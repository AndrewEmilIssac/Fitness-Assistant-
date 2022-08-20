

package com.fitness.assistant.data.databases.entities

import android.os.Build
import androidx.test.platform.app.InstrumentationRegistry
import com.fitness.assistant.data.databases.daos.FoodProductDao
import com.fitness.assistant.data.databases.dbclasses.FoodDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/*
 test class for FoodDatabase that is used when no internet available
 offline database
 */

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
internal class FoodDatabaseTest {


    @Test
    fun testAll() {
        CoroutineScope(IO).launch {
            val foodProductDao: FoodProductDao =
                FoodDatabase.invoke(
                    InstrumentationRegistry.getInstrumentation().targetContext
                ).getProductDao()

            foodLoad(foodProductDao)
        }
    }

    private fun foodLoad(foodProductDao: FoodProductDao) {
        assert(foodProductDao.getAllFood().toString().isNotBlank())
    }


}