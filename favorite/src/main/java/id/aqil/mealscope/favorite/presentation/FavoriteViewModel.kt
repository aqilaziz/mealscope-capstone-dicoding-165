package id.aqil.mealscope.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.aqil.mealscope.core.domain.usecase.MealUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FavoriteViewModel(
    mealUseCase: MealUseCase,
) : ViewModel() {
    val favorites: StateFlow<List<FavoriteMealUi>> = mealUseCase.getFavoriteMeals()
        .map { meals -> meals.map(FavoriteMealUiMapper::mapDomainToUi) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
}
