package id.aqil.mealscope.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.aqil.mealscope.core.data.Resource
import id.aqil.mealscope.core.domain.model.Meal
import id.aqil.mealscope.core.domain.usecase.MealUseCase
import id.aqil.mealscope.presentation.model.MealUiMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val mealUseCase: MealUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(DetailUiState(isLoading = true))
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    private var currentId: String? = null

    fun loadMeal(id: String) {
        if (currentId == id) return
        currentId = id
        observeFavorite(id)
        viewModelScope.launch {
            mealUseCase.getMealDetail(id).collect { resource ->
                val current = _uiState.value
                _uiState.value = when (resource) {
                    Resource.Loading -> current.copy(isLoading = true, message = null)
                    is Resource.Success -> current.copy(
                        isLoading = false,
                        meal = resource.data,
                        mealUi = MealUiMapper.mapDomainToUi(resource.data),
                        message = null,
                    )
                    is Resource.Error -> current.copy(isLoading = false, message = resource.message)
                }
            }
        }
    }

    fun toggleFavorite() {
        val state = _uiState.value
        val meal: Meal = state.meal ?: return
        viewModelScope.launch {
            mealUseCase.setFavoriteMeal(meal, !state.isFavorite)
        }
    }

    private fun observeFavorite(id: String) {
        viewModelScope.launch {
            mealUseCase.isFavorite(id).collect { isFavorite ->
                _uiState.value = _uiState.value.copy(isFavorite = isFavorite)
            }
        }
    }
}
