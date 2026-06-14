package id.aqil.mealscope.favorite.di

import id.aqil.mealscope.favorite.presentation.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}
