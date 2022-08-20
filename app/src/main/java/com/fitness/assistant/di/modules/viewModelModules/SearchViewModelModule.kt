package com.fitness.assistant.di.modules.viewModelModules

import androidx.lifecycle.ViewModel
import com.fitness.assistant.di.other.ViewModelKey
import com.fitness.assistant.viewModels.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun bindSearchVM(searchViewModel: SearchViewModel): ViewModel

}