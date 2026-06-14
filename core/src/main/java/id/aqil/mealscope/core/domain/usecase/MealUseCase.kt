package id.aqil.mealscope.core.domain.usecase

import id.aqil.mealscope.core.data.Resource
import id.aqil.mealscope.core.domain.model.Meal
import kotlinx.coroutines.flow.Flow

interface MealUseCase {
    fun getMeals(): Flow<Resource<List<Meal>>>
    fun getMealDetail(id: String): Flow<Resource<Meal>>
    fun getFavoriteMeals(): Flow<List<Meal>>
    fun isFavorite(id: String): Flow<Boolean>
    suspend fun setFavoriteMeal(meal: Meal, isFavorite: Boolean)
}
