

package com.fitness.assistant.data.models

/**
 * represents possible types of bottom navigation tabs
 */
enum class FragmentNavigationType constructor(var code: Int = 0) {
    PRODUCTS(0),
    HOME(1),
    RECIPES(2),
    SETTINGS(3)
}