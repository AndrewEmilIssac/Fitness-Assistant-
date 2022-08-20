package com.fitness.assistant.di.modules.viewModelModules

import androidx.lifecycle.ViewModel
import com.fitness.assistant.di.other.ViewModelKey
import com.fitness.assistant.viewModels.MyMealsSearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MyMealsSearchViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MyMealsSearchViewModel::class)
    internal abstract fun bindSearchVM(myMealsSearchViewModel: MyMealsSearchViewModel): ViewModel

}