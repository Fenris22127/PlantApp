package de.fenris.plantapp2.data

data class Plant(
    val name: Int,
    val latinName: String,
    val room: List<Room>,
    val water: String,
    val note: Int,
    val waterFrequency: Int,
    val warningSigns: List<WarningSign>,
    val sensitivity: Sensitivity,
    val coverImage: Int,
    val myImage: Int
)