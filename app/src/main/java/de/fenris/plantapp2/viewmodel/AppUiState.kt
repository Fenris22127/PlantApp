package de.fenris.plantapp2.viewmodel

import de.fenris.plantapp2.data.Plant
import de.fenris.plantapp2.data.Room

data class AppUiState(
    val isSettingsOpen: Boolean = false,
    val selectedRoom: Room = Room.ALL_ROOMS,
    val selectedRoomIndex: Int = 0,
    val isPlantDetailsOpen: Boolean = false,
    val plantList: List<Plant> = emptyList(),
    val selectedPlant: Plant? = null
)