package id.aqil.mealscope.di

import id.aqil.mealscope.presentation.detail.DetailViewModel
import id.aqil.mealscope.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
