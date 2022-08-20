package com.fitness.assistant.di.modules

import com.fitness.assistant.ui.fragments.statistics.StatisticsDayFragment
import com.fitness.assistant.ui.fragments.statistics.StatisticsMonthFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class StatisticsFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMonthFragment(): StatisticsMonthFragment
    @ContributesAndroidInjector
    abstract fun contributeDayFragment(): StatisticsDayFragment


}