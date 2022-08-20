

package com.fitness.assistant.di.modules

import com.fitness.assistant.ui.fragments.home.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeUpFragment(): HomeUpFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeDownFragment(): HomeDownFragment

    @ContributesAndroidInjector
    abstract fun contributeDayFragment(): DayFragment

    @ContributesAndroidInjector
    abstract fun contributeMyMealsFragment(): MyMealsFragment

    @ContributesAndroidInjector()
    abstract fun contributeRecipeSearchFragment(): RecipesFragment

    @ContributesAndroidInjector()
    abstract fun contributeSettingsFragment(): Settings

}