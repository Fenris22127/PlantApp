package de.fenris.plantapp2.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.time.LocalDate

data class Task(
    val plant: Plant,
    var watered: MutableState<Boolean> = mutableStateOf(false),
    var startDate: LocalDate,
    var endDate: LocalDate,
)