package de.fenris.plantapp2.organisation

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import de.fenris.plantapp2.R
import de.fenris.plantapp2.data.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val uiState = MutableStateFlow(TaskListViewState())

    fun consumableState() = uiState.asStateFlow()

    init {
        fetchTaskData()
    }

    fun handleViewEvent(viewEvent: TaskListViewEvent) {
        when (viewEvent) {
            is TaskListViewEvent.ToggleItemOne -> {
                val currentList = uiState.value.weekOne.toMutableList()
                uiState.value = uiState.value.copy(weekOne = currentList)
            }

            is TaskListViewEvent.ToggleItemTwo -> {
                val currentList = uiState.value.weekTwo.toMutableList()
                uiState.value = uiState.value.copy(weekTwo = currentList)
            }
        }
    }

    var listOne: MutableList<Task> = mutableListOf()
    var listTwo: MutableList<Task> = mutableListOf()
    private fun fetchTaskData() {
        viewModelScope.launch {
            val plantListOne = PlantList.instance
            if (plantListOne.allPlants.isEmpty()) {
                plantListOne.setData()
            }
            listOne = plantListOne.allPlants
                .sortedBy { it.name }
                .filter {
                    it.waterFrequency == R.string.once_a_week ||
                            it.waterFrequency == R.string.one_to_two_times_a_week
                }
                .map {
                    Task(
                        it,
                        mutableStateOf(false),
                        LocalDate.of(2023, 7, 29),
                        LocalDate.of(2023, 7, 31)
                    )
                }
                .toMutableList()
            val plantListTwo = PlantList.instance
            if (plantListTwo.allPlants.isEmpty()) {
                plantListTwo.setData()
            }
            listTwo = plantListTwo.allPlants
                .sortedBy { it.name }
                .filter {
                    it.waterFrequency == R.string.once_a_week ||
                            it.waterFrequency == R.string.one_to_two_times_a_week ||
                            it.waterFrequency == R.string.once_in_2_weeks
                }
                .map {
                    Task(
                        it,
                        mutableStateOf(false),
                        LocalDate.of(2023, 8, 5),
                        LocalDate.of(2023, 8, 7)
                    )
                }
                .toMutableList()
            uiState.value =
                uiState.value.copy(isLoading = false, weekOne = listOne, weekTwo = listTwo)
        }
    }

}

data class TaskListViewState(
    val isLoading: Boolean = true,
    val weekOne: List<Task> = emptyList(),
    val weekTwo: List<Task> = emptyList(),
    val errorMessage: String? = null
)

sealed class TaskListViewEvent {
    data class ToggleItemOne(val task: Task) : TaskListViewEvent()
    data class ToggleItemTwo(val task: Task) : TaskListViewEvent()
}
