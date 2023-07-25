package de.fenris.plantapp2.viewmodel.test

import androidx.lifecycle.ViewModel
import de.fenris.plantapp2.data.Plant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SliderViewModel(
    selectedPlant: Plant
) : ViewModel() {

    private val _uiState = MutableStateFlow(SliderUiState())
    val uiState: StateFlow<SliderUiState> = _uiState.asStateFlow()
    private val maxLargeDots = 3

    init {
        fetchDotData(selectedPlant)
    }

    fun previousImage(newIndex: Int) {
        _uiState.value = _uiState.value.copy(currentIndex = newIndex)
        if (newIndex < uiState.value.startIndex) {
            _uiState.value = _uiState.value.copy(
                startIndex = uiState.value.startIndex - 1,
                endIndex = uiState.value.endIndex - 1
            )
        }
        val sizeList = adjustList(
            _uiState.value.sizeList.toMutableList(),
            _uiState.value.currentIndex,
            _uiState.value.startIndex,
            _uiState.value.endIndex
        )
        _uiState.value = _uiState.value.copy(sizeList = sizeList)
    }

    fun nextImage(newIndex: Int) {
        if (newIndex < _uiState.value.startIndex) {
            _uiState.value = _uiState.value.copy(
                currentIndex = newIndex,
                startIndex = _uiState.value.startIndex + 1,
                endIndex = _uiState.value.endIndex + 1
            )
        }
        val sizeList = adjustList(
            _uiState.value.sizeList.toMutableList(),
            _uiState.value.currentIndex,
            _uiState.value.startIndex,
            _uiState.value.endIndex
        )
        _uiState.value = _uiState.value.copy(sizeList = sizeList)
    }

    private fun createList(listSize: Int): MutableList<Int> {
        val sizeList = mutableListOf(0)
        if (listSize <= maxLargeDots) {
            for (i in 1..listSize) {
                sizeList.add(0)
            }
        }
        if (listSize == maxLargeDots + 1) {
            for (i in 1 until maxLargeDots) {
                sizeList.add(0)
            }
            sizeList.add(1)
        }
        if (listSize == maxLargeDots + 2) {
            for (i in 1 until maxLargeDots) {
                sizeList.add(0)
            }
            sizeList.add(1)
            sizeList.add(2)
        }
        if (listSize >= maxLargeDots + 3) {
            for (i in 1 until maxLargeDots) {
                sizeList.add(0)
            }
            sizeList.add(1)
            sizeList.add(2)
            for (i in maxLargeDots + 2 until listSize) {
                sizeList.add(3)
            }
        }
        return sizeList
    }

    private fun adjustList(
        list: MutableList<Int>,
        currentPage: Int,
        startRange: Int,
        endRange: Int
    ): MutableList<Int> {
        var adjustingEndIndex = endRange
        var adjustingStartIndex = startRange
        list[currentPage] = 0
        if (adjustingEndIndex + 1 < list.size) {
            list[adjustingEndIndex + 1] = 1
            adjustingEndIndex += 1
        }
        if (adjustingEndIndex + 1 < list.size) {
            list[adjustingEndIndex + 1] = 2
            adjustingEndIndex += 1
        }
        for (j in adjustingEndIndex + 1 until list.size) {
            list[j] = 3
        }
        if (adjustingStartIndex - 1 >= 0) {
            list[adjustingStartIndex - 1] = 1
            adjustingStartIndex -= 1
        }
        if (adjustingStartIndex - 1 >= 0) {
            list[adjustingStartIndex - 1] = 2
            adjustingStartIndex -= 1
        }
        for (j in adjustingEndIndex + 1 until list.size) {
            list[j] = 3
        }
        for (j in adjustingStartIndex - 1 downTo 0) {
            list[j] = 3
        }
        return list
    }

    private fun fetchDotData(selectedPlant: Plant) {
        val imagesList = mutableListOf(selectedPlant.coverImage)
        imagesList.addAll(selectedPlant.myImage)
        _uiState.value = _uiState.value.copy(
            selectedPlant = selectedPlant,
            sizeList = createList(imagesList.size),
        )
    }
}