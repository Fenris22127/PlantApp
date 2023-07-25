package de.fenris.plantapp2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import de.fenris.plantapp2.data.Plant
import de.fenris.plantapp2.data.PlantList
import de.fenris.plantapp2.data.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()
    private var allPlants: List<Plant> = emptyList()

    init {
        fetchPlantListData()
    }

    fun openSettings() {
        _uiState.value = _uiState.value.copy(isSettingsOpen = true)
    }

    fun closeSettings() {
        _uiState.value = _uiState.value.copy(isSettingsOpen = false)
    }

    fun openPlantDetails() {
        _uiState.value = _uiState.value.copy(isPlantDetailsOpen = true)
    }

    fun closePlantDetails() {
        _uiState.value = _uiState.value.copy(isPlantDetailsOpen = false)
    }

    fun updateSelectedPlant(selectedPlant: Plant) {
        _uiState.value = _uiState.value.copy(selectedPlant = selectedPlant)
    }

    fun updateSelectedRoom(selectedRoom: Room, selectedRoomIndex: Int) {
        val filteredPlants: List<Plant> =
            if (selectedRoom == Room.ALL_ROOMS) {
                allPlants
            } else {
                allPlants.filter { it.room.contains(selectedRoom) }.toList().sortedBy { it.name }
            }
        _uiState.value = _uiState.value.copy(
            plantList = filteredPlants,
            selectedRoom = selectedRoom,
            selectedRoomIndex = selectedRoomIndex
        )
    }

    private fun fetchPlantListData() {

        viewModelScope.launch {
            val plantList = PlantList.instance
            if (plantList.allPlants.isEmpty()) {
                plantList.setData()
            }
            allPlants =
                plantList.allPlants.sortedBy { getApplication<Application>().resources.getString(it.name) }
            _uiState.value =
                _uiState.value.copy(plantList = allPlants)
        }
    }
}