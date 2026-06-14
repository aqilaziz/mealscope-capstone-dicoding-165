package id.aqil.mealscope.core.domain.usecase

import id.aqil.mealscope.core.data.Resource
import id.aqil.mealscope.core.domain.model.Meal
import id.aqil.mealscope.core.domain.repository.IMealRepository
import kotlinx.coroutines.flow.Flow

class MealInteractor(
    private val repository: IMealRepository,
) : MealUseCase {
    override fun getMeals(): Flow<Resource<List<Meal>>> = repository.getMeals()

    override fun getMealDetail(id: String): Flow<Resource<Meal>> = repository.getMealDetail(id)

    override fun getFavoriteMeals(): Flow<List<Meal>> = repository.getFavoriteMeals()

    override fun isFavorite(id: String): Flow<Boolean> = repository.isFavorite(id)

    override suspend fun setFavoriteMeal(meal: Meal, isFavorite: Boolean) {
        repository.setFavoriteMeal(meal, isFavorite)
    }
}
