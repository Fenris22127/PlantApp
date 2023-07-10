package de.fenris.plantapp2.viewmodel

import de.fenris.plantapp2.data.Room

data class AppUiState(
    val isSettingsOpen: Boolean = false,
    val selectedRoom: Room = Room.ALL_ROOMS,
    val plantDetailsOpen: Boolean = false,
)