package id.aqil.mealscope.core.data.remote.network

import id.aqil.mealscope.core.data.remote.response.MealDetailResponse
import id.aqil.mealscope.core.data.remote.response.MealListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("filter.php")
    suspend fun getMealsByCategory(
        @Query("c") category: String = "Seafood",
    ): MealListResponse

    @GET("lookup.php")
    suspend fun getMealDetail(
        @Query("i") id: String,
    ): MealDetailResponse
}
