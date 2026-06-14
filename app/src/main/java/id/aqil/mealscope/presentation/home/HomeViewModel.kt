package id.aqil.mealscope.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.aqil.mealscope.core.data.Resource
import id.aqil.mealscope.core.domain.usecase.MealUseCase
import id.aqil.mealscope.presentation.model.MealUiMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val mealUseCase: MealUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadMeals()
    }

    fun loadMeals() {
        viewModelScope.launch {
            mealUseCase.getMeals().collect { resource ->
                _uiState.value = when (resource) {
                    Resource.Loading -> HomeUiState(isLoading = true)
                    is Resource.Success -> HomeUiState(
                        meals = resource.data.map(MealUiMapper::mapDomainToUi),
                        message = if (resource.data.isEmpty()) "No meals found." else null,
                    )
                    is Resource.Error -> HomeUiState(message = resource.message)
                }
            }
        }
    }
}
