package com.fitness.assistant.data.api.models.recipedetailed.nn

class WinePairing(
    val pairedWines: List<String> = listOf(),
    val pairingText: String = "",
    val productMatches: List<ProductMatche> = listOf()
)