

package com.fitness.assistant.viewModels

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitness.assistant.data.interfaces.BasicOperationCallback
import com.fitness.assistant.data.models.Event
import com.fitness.assistant.data.models.Product
import com.fitness.assistant.data.models.StatisticsNutrientType
import com.fitness.assistant.data.models.day.Day
import com.fitness.assistant.data.models.day.DayDate
import com.fitness.assistant.data.repositories.DayRepository
import com.fitness.assistant.data.repositories.StatisticsRepository
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.PieDataSet
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

/**
 * ViewModel is shared by StatisticsActivity, StatisticsMonthFragment and StatisticsDayFragment
 */

@SuppressLint("DefaultLocale")
class StatisticsViewModel @Inject constructor(
    private val statisticsRepository: StatisticsRepository,
    private val dayRepository: DayRepository
) : ViewModel() {


    val barDataSet: LiveData<BarDataSet> = statisticsRepository.barDataSet
    val pieDataSet: LiveData<PieDataSet> = statisticsRepository.pieDataSet
    val products: LiveData<ArrayList<Product>> = statisticsRepository.products

    val monthPosition: LiveData<Int> = statisticsRepository.monthPosition
    val nutrientPosition: LiveData<StatisticsNutrientType> = statisticsRepository.nutrient

    // check if day data has been changed
    val dataChanged = MutableLiveData(false)

    private fun updateProducts(day: Day) = statisticsRepository.updateProducts(day)


    fun getNewPieData(date: DayDate?) =
        viewModelScope.launch(IO) {
            date ?: return@launch
            val day = dayRepository.getDayByDate(date) ?: return@launch
            statisticsRepository.setNewPieDataset(day)
            updateProducts(day)
        }

    fun updateBarDataset(
        nutrientInt: Int? = null,
        monthInt: Int? = null
    ) = statisticsRepository.updateBarDataset(nutrientInt, monthInt)

    private val _deleteProduct = MutableLiveData<Event<String>>()

    val deleteProduct: LiveData<Event<String>>
        get() = _deleteProduct

    private val deleteCallback = object : BasicOperationCallback {
        override fun onSuccess() {
        }

        override fun onFailure() {
            _deleteProduct.value = Event("")
        }
    }

    fun deleteProduct(date: DayDate, product: Product) =
        viewModelScope.launch(IO) {
            // if product has been deleted, delete it from LiveData
            if (dayRepository.deleteProductFromDay(date, product, deleteCallback)) {
                statisticsRepository.deleteProductFromProducts(product)
            }
        }


    fun getDateToDisplay(dayDate: DayDate): CharSequence {
        return "${dayDate.day}/${dayDate.month}"
    }

}