package id.aqil.mealscope.presentation.model

import id.aqil.mealscope.core.domain.model.Meal

object MealUiMapper {
    fun mapDomainToUi(input: Meal): MealUi {
        val subtitle = listOf(input.category, input.area)
            .filter { it.isNotBlank() }
            .joinToString(" - ")
            .ifBlank { "Meal inspiration" }
        return MealUi(
            id = input.id,
            title = input.name,
            imageUrl = input.thumbnail,
            subtitle = subtitle,
            description = input.instructions,
        )
    }
}
