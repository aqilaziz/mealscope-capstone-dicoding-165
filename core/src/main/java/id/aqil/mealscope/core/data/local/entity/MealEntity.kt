package id.aqil.mealscope.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_meals")
data class MealEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val thumbnail: String,
    val category: String,
    val area: String,
    val instructions: String,
)
