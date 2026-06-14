package id.aqil.mealscope.core.data

import id.aqil.mealscope.core.data.local.room.MealDao
import id.aqil.mealscope.core.data.remote.network.ApiService
import id.aqil.mealscope.core.domain.model.Meal
import id.aqil.mealscope.core.domain.repository.IMealRepository
import id.aqil.mealscope.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class MealRepository(
    private val apiService: ApiService,
    private val mealDao: MealDao,
) : IMealRepository {
    override fun getMeals(): Flow<Resource<List<Meal>>> = flow {
        emit(Resource.Loading)
        val response = apiService.getMealsByCategory()
        val meals = DataMapper.mapListDtoToDomain(response.meals.orEmpty())
        emit(Resource.Success(meals))
    }.catch { error ->
        emit(Resource.Error(error.message ?: "Unable to load meals."))
    }.flowOn(Dispatchers.IO)

    override fun getMealDetail(id: String): Flow<Resource<Meal>> = flow {
        emit(Resource.Loading)
        val response = apiService.getMealDetail(id)
        val meal = response.meals.orEmpty().firstOrNull()
        if (meal == null) {
            emit(Resource.Error("Meal detail is not available."))
        } else {
            emit(Resource.Success(DataMapper.mapDetailDtoToDomain(meal)))
        }
    }.catch { error ->
        emit(Resource.Error(error.message ?: "Unable to load meal detail."))
    }.flowOn(Dispatchers.IO)

    override fun getFavoriteMeals(): Flow<List<Meal>> {
        return mealDao.getFavoriteMeals().map(DataMapper::mapEntitiesToDomain)
    }

    override fun isFavorite(id: String): Flow<Boolean> {
        return mealDao.isFavorite(id)
    }

    override suspend fun setFavoriteMeal(meal: Meal, isFavorite: Boolean) {
        if (isFavorite) {
            mealDao.insertFavorite(DataMapper.mapDomainToEntity(meal))
        } else {
            mealDao.deleteFavorite(meal.id)
        }
    }
}
