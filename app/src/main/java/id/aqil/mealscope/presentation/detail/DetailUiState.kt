package id.aqil.mealscope.presentation.detail

import id.aqil.mealscope.core.domain.model.Meal
import id.aqil.mealscope.presentation.model.MealUi

data class DetailUiState(
    val isLoading: Boolean = false,
    val meal: Meal? = null,
    val mealUi: MealUi? = null,
    val isFavorite: Boolean = false,
    val message: String? = null,
)
