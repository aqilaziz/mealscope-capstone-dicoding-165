package id.aqil.mealscope.core.utils

import id.aqil.mealscope.core.data.local.entity.MealEntity
import id.aqil.mealscope.core.data.remote.response.MealDetailDto
import id.aqil.mealscope.core.data.remote.response.MealListItemDto
import id.aqil.mealscope.core.domain.model.Meal

object DataMapper {
    fun mapListDtoToDomain(input: List<MealListItemDto>): List<Meal> {
        return input.mapNotNull { item ->
            val id = item.id.orEmpty()
            val name = item.name.orEmpty()
            if (id.isBlank() || name.isBlank()) return@mapNotNull null
            Meal(
                id = id,
                name = name,
                thumbnail = item.thumbnail.orEmpty(),
                category = "Seafood",
                area = "International",
                instructions = "Open the detail page to read the cooking instructions.",
            )
        }
    }

    fun mapDetailDtoToDomain(input: MealDetailDto): Meal {
        return Meal(
            id = input.id.orEmpty(),
            name = input.name.orEmpty(),
            thumbnail = input.thumbnail.orEmpty(),
            category = input.category.orEmpty().ifBlank { "Unknown category" },
            area = input.area.orEmpty().ifBlank { "Unknown area" },
            instructions = input.instructions.orEmpty().ifBlank { "No instructions available." },
        )
    }

    fun mapEntitiesToDomain(input: List<MealEntity>): List<Meal> {
        return input.map { mapEntityToDomain(it) }
    }

    fun mapEntityToDomain(input: MealEntity): Meal {
        return Meal(
            id = input.id,
            name = input.name,
            thumbnail = input.thumbnail,
            category = input.category,
            area = input.area,
            instructions = input.instructions,
        )
    }

    fun mapDomainToEntity(input: Meal): MealEntity {
        return MealEntity(
            id = input.id,
            name = input.name,
            thumbnail = input.thumbnail,
            category = input.category,
            area = input.area,
            instructions = input.instructions,
        )
    }
}
