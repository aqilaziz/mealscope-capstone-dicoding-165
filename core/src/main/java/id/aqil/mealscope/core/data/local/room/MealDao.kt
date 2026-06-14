package id.aqil.mealscope.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.aqil.mealscope.core.data.local.entity.MealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Query("SELECT * FROM favorite_meals ORDER BY name ASC")
    fun getFavoriteMeals(): Flow<List<MealEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_meals WHERE id = :id)")
    fun isFavorite(id: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(meal: MealEntity)

    @Query("DELETE FROM favorite_meals WHERE id = :id")
    suspend fun deleteFavorite(id: String)
}
