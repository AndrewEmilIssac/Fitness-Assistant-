package com.fitness.assistant.data.api.models.recipedetailed.nn

import com.fitness.assistant.data.api.models.recipedetailed.nn.Step

class AnalyzedInstruction constructor(
    val name: String = "",
    val steps: List<Step> = listOf()
)