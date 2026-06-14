package id.aqil.mealscope.favorite.presentation

import id.aqil.mealscope.core.domain.model.Meal

object FavoriteMealUiMapper {
    fun mapDomainToUi(input: Meal): FavoriteMealUi {
        return FavoriteMealUi(
            id = input.id,
            title = input.name,
            imageUrl = input.thumbnail,
            subtitle = listOf(input.category, input.area).filter { it.isNotBlank() }.joinToString(" - "),
        )
    }
}
