package id.aqil.mealscope.core.domain.model

data class Meal(
    val id: String,
    val name: String,
    val thumbnail: String,
    val category: String,
    val area: String,
    val instructions: String,
)
