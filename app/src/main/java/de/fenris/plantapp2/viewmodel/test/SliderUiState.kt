package de.fenris.plantapp2.viewmodel.test

import de.fenris.plantapp2.data.Plant

data class SliderUiState(
    val startIndex: Int = 0,
    val endIndex: Int = 0,
    val currentIndex: Int = 0,
    val selectedPlant: Plant? = null,
    val dots: Unit = Unit,
    val sizeList: List<Int> = emptyList()
)