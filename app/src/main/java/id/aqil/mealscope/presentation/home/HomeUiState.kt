package id.aqil.mealscope.presentation.home

import id.aqil.mealscope.presentation.model.MealUi

data class HomeUiState(
    val isLoading: Boolean = false,
    val meals: List<MealUi> = emptyList(),
    val message: String? = null,
)
