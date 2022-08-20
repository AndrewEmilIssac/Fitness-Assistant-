package com.fitness.assistant.di.modules.viewModelModules

import androidx.lifecycle.ViewModel
import com.fitness.assistant.di.other.ViewModelKey
import com.fitness.assistant.viewModels.AddCustomProductViewModel
import com.fitness.assistant.viewModels.DetailedRecipeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class DetailedRecipeVMModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailedRecipeViewModel::class)
    internal abstract fun bindDetailedRecipeViewModel(detailedRecipeViewModel: DetailedRecipeViewModel): ViewModel

}