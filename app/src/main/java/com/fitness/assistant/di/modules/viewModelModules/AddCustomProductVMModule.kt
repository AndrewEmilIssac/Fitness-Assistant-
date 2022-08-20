package com.fitness.assistant.di.modules.viewModelModules

import androidx.lifecycle.ViewModel
import com.fitness.assistant.di.other.ViewModelKey
import com.fitness.assistant.viewModels.AddCustomProductViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AddCustomProductVMModule {

    @Binds
    @IntoMap
    @ViewModelKey(AddCustomProductViewModel::class)
    internal abstract fun bindAddCustomViewModel(addCustomProductViewModel: AddCustomProductViewModel): ViewModel

}