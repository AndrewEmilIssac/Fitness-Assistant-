package com.fitness.assistant.di.modules.viewModelModulesimport androidx.lifecycle.ViewModelimport com.fitness.assistant.di.other.ViewModelKeyimport com.fitness.assistant.viewModels.HomeViewModelimport com.fitness.assistant.viewModels.MyMealsViewModelimport com.fitness.assistant.viewModels.RecipeSearchViewModelimport com.fitness.assistant.viewModels.SettingsViewModelimport dagger.Bindsimport dagger.Moduleimport dagger.multibindings.IntoMap@Moduleabstract class HomeActivityVMModules {    @Binds    @IntoMap    @ViewModelKey(HomeViewModel::class)    internal abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel    @Binds    @IntoMap    @ViewModelKey(MyMealsViewModel::class)    internal abstract fun bindMyMealsViewModel(myMealsViewModel: MyMealsViewModel): ViewModel    @Binds    @IntoMap    @ViewModelKey(RecipeSearchViewModel::class)    internal abstract fun bindRecipeSearchViewModelModule(recipeSearchViewModel: RecipeSearchViewModel): ViewModel    @Binds    @IntoMap    @ViewModelKey(SettingsViewModel::class)    internal abstract fun bindSettingsVM(settingsViewModel: SettingsViewModel): ViewModel}