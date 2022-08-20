package com.fitness.assistant.di.modules.viewModelModules

import androidx.lifecycle.ViewModel
import com.fitness.assistant.di.other.ViewModelKey
import com.fitness.assistant.viewModels.StatisticsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class StatisticsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(StatisticsViewModel::class)
    internal abstract fun bindStatisticsViewModelModule(statisticsViewModel: StatisticsViewModel): ViewModel

}