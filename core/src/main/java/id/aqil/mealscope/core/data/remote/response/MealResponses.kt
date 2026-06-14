package id.aqil.mealscope.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class MealListResponse(
    @SerializedName("meals")
    val meals: List<MealListItemDto>?,
)

data class MealDetailResponse(
    @SerializedName("meals")
    val meals: List<MealDetailDto>?,
)

data class MealListItemDto(
    @SerializedName("idMeal")
    val id: String?,
    @SerializedName("strMeal")
    val name: String?,
    @SerializedName("strMealThumb")
    val thumbnail: String?,
)

data class MealDetailDto(
    @SerializedName("idMeal")
    val id: String?,
    @SerializedName("strMeal")
    val name: String?,
    @SerializedName("strMealThumb")
    val thumbnail: String?,
    @SerializedName("strCategory")
    val category: String?,
    @SerializedName("strArea")
    val area: String?,
    @SerializedName("strInstructions")
    val instructions: String?,
)
