package de.fenris.plantapp2.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import de.fenris.plantapp2.R
import de.fenris.plantapp2.Utils
import de.fenris.plantapp2.data.Task
import de.fenris.plantapp2.viewmodel.TaskListViewEvent
import de.fenris.plantapp2.viewmodel.TaskViewModel
import de.fenris.plantapp2.navigation.TopBar
import de.fenris.plantapp2.ui.theme.getNavColor
import de.fenris.plantapp2.ui.theme.getOnInverseSurface

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskScreen(viewModel: TaskViewModel) {
    Box(Modifier.padding(10.dp, 10.dp, 10.dp, 10.dp)) {
        Utils.GetBackgroundImage()

        val viewState = viewModel.consumableState().collectAsState()

        Box(Modifier.fillMaxSize()) {
            LazyColumn {
                val weekOne = viewState.value.weekOne
                    .filter {
                        it.plant.waterFrequency == R.string.once_a_week ||
                                it.plant.waterFrequency == R.string.one_to_two_times_a_week
                    }
                    .groupBy { it.watered.value }
                val weekTwo = viewState.value.weekTwo
                    .filter {
                        it.plant.waterFrequency == R.string.once_a_week ||
                                it.plant.waterFrequency == R.string.one_to_two_times_a_week ||
                                it.plant.waterFrequency == R.string.once_in_2_weeks
                    }
                    .groupBy { it.watered.value }
                val weekOneWatered = weekOne[true] ?: emptyList()
                val weekOneNotWatered = weekOne[false] ?: emptyList()
                val weekTwoWatered = weekTwo[true] ?: emptyList()
                val weekTwoNotWatered = weekTwo[false] ?: emptyList()

                item {
                    GetWeekTitle(textId = R.string.week_one_span_title)
                }
                item {
                    Divider()
                }
                if (weekOneNotWatered.isEmpty()) {
                    item {
                        GetEmpty(textId = R.string.no_unfinished_tasks)
                    }
                }
                items(weekOneNotWatered, key = { item -> item.plant.name }) { task ->
                    GetTasks4Plants(
                        task = task,
                        onCheckboxClicked = {
                            viewModel.handleViewEvent(TaskListViewEvent.ToggleItemOne(it))
                        },
                        modifier = Modifier.animateItemPlacement()
                    )
                }
                item {
                    GetCompleteHeader(R.string.task_completed)
                }
                item {
                    Divider(Modifier.padding(5.dp, 0.dp))
                }
                if (weekOneWatered.isEmpty()) {
                    item {
                        GetEmpty(textId = R.string.no_tasks_completed_yet)
                    }
                }
                items(weekOneWatered) { task ->
                    GetTasks4Plants(
                        task = task,
                        onCheckboxClicked = {
                            viewModel.handleViewEvent(TaskListViewEvent.ToggleItemOne(it))
                        }, modifier = Modifier.animateItemPlacement()
                    )
                }
                item {
                    GetWeekTitle(textId = R.string.week_two_span_title)
                }
                item {
                    Divider()
                }
                items(weekTwoNotWatered) { task ->
                    GetTasks4Plants(
                        task = task,
                        onCheckboxClicked = {
                            viewModel.handleViewEvent(TaskListViewEvent.ToggleItemTwo(it))
                        }, modifier = Modifier.animateItemPlacement()
                    )
                }
                item {
                    GetCompleteHeader(R.string.task_completed)
                }
                item {
                    Divider(Modifier.padding(5.dp, 0.dp))
                }
                if (weekTwoWatered.isEmpty()) {
                    item {
                        GetEmpty(textId = R.string.no_tasks_completed_yet)
                    }
                }
                items(weekTwoWatered) { task ->
                    GetTasks4Plants(
                        task = task,
                        onCheckboxClicked = {
                            viewModel.handleViewEvent(TaskListViewEvent.ToggleItemTwo(it))
                        }, modifier = Modifier.animateItemPlacement()
                    )
                }
            }
            if (viewState.value.isLoading) {
                LoadingIndicator()
            }
        }
    }
}

@Composable
fun LoadingIndicator() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun GetEmpty(textId: Int) {
    Text(
        text = stringResource(textId),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp, 10.dp, 5.dp, 5.dp),
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f)
    )
}

@Composable
fun GetCompleteHeader(textId: Int) {
    Text(
        text = stringResource(textId),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp, 10.dp, 5.dp, 5.dp),
        fontSize = 18.sp,
        fontWeight = FontWeight(500),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f)
    )
}

@Composable
fun GetWeekTitle(textId: Int) {
    Text(
        text = stringResource(id = textId),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp, 10.dp, 5.dp, 5.dp),
        fontSize = 24.sp,
        fontWeight = FontWeight(500),
        color = MaterialTheme.colorScheme.onSurface,
        letterSpacing = 0.15.sp
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetTasks4Plants(
    task: Task,
    onCheckboxClicked: (task: Task) -> Unit,
    modifier: Modifier = Modifier
) {
    var showDialog by remember {
        mutableStateOf(false)
    }
    if (showDialog) {
        Dialog(
            onDismissRequest = { showDialog = false },
            properties = DialogProperties(
                dismissOnBackPress = true,
                usePlatformDefaultWidth = false
            ),
            ) {
            Scaffold(
                topBar = {
                    TopBar(
                        title = stringResource(task.plant.name),
                        closeIcon = true,
                        onDismissRequest = { showDialog = false }
                    )
                },
            ) {
                Box(Modifier.padding(it)) {
                    DetailsScreen(task.plant)
                }
            }
        }
    }

    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = getNavColor().copy(alpha = 0.95f).compositeOver(Color.White),
        ),
        modifier = modifier
            .clickable(onClick = { showDialog = true })
            .padding(5.dp)
    ) {
        ListItem(
            leadingContent = {
                Image(
                    painter = painterResource(id = task.plant.coverImage),
                    contentDescription = "Plant",
                    modifier = Modifier
                        .padding(0.dp, 7.dp, 0.dp, 5.dp)
                        .width(60.dp),
                    alignment = Alignment.Center,
                    colorFilter = if (task.watered.value) {
                        ColorFilter.tint(Color.LightGray, BlendMode.Hardlight)
                    } else {
                        null
                    }
                )
            },
            headlineText = {
                Text(
                    text = stringResource(task.plant.name),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(10.dp, 0.dp, 0.dp, 0.dp)
                        .widthIn(0.dp, 230.dp),
                    color = if (task.watered.value) {
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    },
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis,
                )
            },
            supportingText = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.WaterDrop,
                        contentDescription = "Water frequency icon",
                        modifier = Modifier
                            .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            .size(14.dp),
                        tint = if (task.watered.value) {
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        }
                    )
                    androidx.compose.material3.Text(
                        text = stringResource(task.plant.waterFrequency),
                        fontSize = 14.sp,
                        modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp),
                        color = if (task.watered.value) {
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        }
                    )
                }
            },
            trailingContent = {
                OutlinedIconToggleButton(
                    checked = task.watered.value,
                    onCheckedChange = {
                        task.watered.value = it
                        if (it) {
                            onCheckboxClicked(task)
                        }
                    },
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 0.dp, 0.dp)
                        .size(30.dp),
                    border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.outline),
                    colors = IconButtonDefaults.outlinedIconToggleButtonColors(
                        containerColor = Color.Transparent,
                        checkedContainerColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                            alpha = 0.5f
                        ),
                        checkedContentColor = getOnInverseSurface(),
                        contentColor = Color.Transparent,
                    ),
                    content = {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = "Checked",
                            modifier = Modifier
                                .padding(0.dp, 0.dp, 0.dp, 0.dp)
                                .size(20.dp),
                        )
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 0.dp),
            colors = ListItemDefaults.colors(
                containerColor = getNavColor().copy(alpha = 0.95f).compositeOver(Color.White),
            )
        )
    }
}