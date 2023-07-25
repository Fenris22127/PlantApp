package de.fenris.plantapp2.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.fenris.plantapp2.navigation.TopBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.StickyNote2
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import de.fenris.plantapp2.R
import de.fenris.plantapp2.Utils
import de.fenris.plantapp2.data.*

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.*
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import de.fenris.plantapp2.ui.theme.getSurfaceContainer
import de.fenris.plantapp2.ui.theme.getTextFieldBackground
import de.fenris.plantapp2.viewmodel.AppUiState
import de.fenris.plantapp2.viewmodel.AppViewModel

@Composable
fun ListScreen() {
    Box(Modifier.padding(10.dp, 10.dp, 10.dp, 10.dp)) {
        Utils.GetBackgroundImage()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.TopCenter)
        ) {
            SearchView()
            FilterPlantList()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    appViewModel: AppViewModel = viewModel()
) {
    val appUiState by appViewModel.uiState.collectAsState()
    val listItems: List<String> = Room.values().map { Utils.getRoom(it) }.toList()
    val context = LocalContext.current
    var expanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier
            .padding(5.dp, 10.dp)
            .wrapContentSize(),
    ) {
        TextField(
            modifier = Modifier
                .width(200.dp)
                .menuAnchor(),
            label = {
                Text(
                    stringResource(R.string.selected_room),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                        .compositeOver(MaterialTheme.colorScheme.onSurfaceVariant)
                )
            },
            value = appUiState.selectedRoom.toString(context = context),
            onValueChange = {},
            readOnly = true,
            enabled = false,
            trailingIcon = {
                if (expanded) {
                    Icon(
                        Icons.Filled.ArrowDropDown,
                        contentDescription = "Arrow Drop Up",
                        tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                            .compositeOver(MaterialTheme.colorScheme.onSurfaceVariant),
                        modifier = Modifier
                            .rotate(180f)
                    )
                } else {
                    Icon(
                        Icons.Filled.ArrowDropDown,
                        contentDescription = "Arrow Drop Down",
                        tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                            .compositeOver(MaterialTheme.colorScheme.onSurfaceVariant)
                    )
                }
            },

            colors = TextFieldDefaults.textFieldColors(
                containerColor = getTextFieldBackground(),
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                disabledIndicatorColor = MaterialTheme.colorScheme.primary,
                errorIndicatorColor = MaterialTheme.colorScheme.error,
                cursorColor = MaterialTheme.colorScheme.secondary,
                textColor = MaterialTheme.colorScheme.onSurface,
                placeholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
            ),
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(getSurfaceContainer())
        ) {
            listItems.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = item,
                            fontWeight = if (index == appUiState.selectedRoomIndex) FontWeight.Bold else null
                        )
                    },
                    onClick = {
                        expanded = false
                        appViewModel.updateSelectedRoom(Room.values()[index], index)
                    },
                )
            }
        }
    }
}

@Composable
fun FilterPlantList(
    appViewModel: AppViewModel = viewModel()
) {
    val appUiState by appViewModel.uiState.collectAsState()
    val plantList = PlantList.instance
    if (plantList.allPlants.isEmpty()) {
        plantList.setData()
    }

    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp, 0.dp, 0.dp, 0.dp)) {
        items(appUiState.plantList) { plant ->
            GetCells4Plants(plant, appViewModel, appUiState)
            Divider()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetCells4Plants(plant: Plant, appViewModel: AppViewModel, appUiState: AppUiState) {
    if (appUiState.isPlantDetailsOpen) {
        Dialog(
            onDismissRequest = { appViewModel.closePlantDetails() },
            properties = DialogProperties(
                dismissOnBackPress = true,
                usePlatformDefaultWidth = false
            ),

            ) {
            Scaffold(
                topBar = {
                    TopBar(
                        title = stringResource(appUiState.selectedPlant!!.name),
                        closeIcon = true,
                        onDismissRequest = { appViewModel.closePlantDetails() }
                    )
                },
            ) {
                Box(Modifier.padding(it)) {
                    DetailsScreen(appUiState.selectedPlant!!)
                }
            }
        }
    }

    Row(Modifier.clickable(
        onClick = {
            appViewModel.openPlantDetails()
            appViewModel.updateSelectedPlant(plant)
        }
    )) {
        Image(
            painter = painterResource(id = plant.coverImage),
            contentDescription = "Plant",
            modifier = Modifier
                .padding(0.dp, 7.dp, 0.dp, 5.dp)
                .width(160.dp),
            alignment = Alignment.Center
        )
        Column {
            Text(
                text = stringResource(plant.name),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp, 7.dp, 0.dp, 0.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = "Room Icon",
                    modifier = Modifier
                        .padding(10.dp, 0.dp, 0.dp, 0.dp)
                        .size(14.dp),
                    tint = Color(0xFF909090)
                )
                Text(
                    text = Utils.roomsToString(plant.room),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp),
                    color = Color(0xFF909090)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Filled.WaterDrop,
                    contentDescription = "Water frequency icon",
                    modifier = Modifier
                        .padding(10.dp, 0.dp, 0.dp, 0.dp)
                        .size(14.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = stringResource(plant.waterFrequency),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Row {
                Icon(
                    Icons.Outlined.StickyNote2,
                    contentDescription = "Note icon",
                    modifier = Modifier
                        .padding(10.dp, 3.dp, 0.dp, 0.dp)
                        .size(14.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = if (stringResource(plant.note) == "\"\"") "" else stringResource(plant.note),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp),
                    lineHeight = 16.sp,
                    softWrap = true,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Filled.Shield,
                    contentDescription = "Shield icon",
                    modifier = Modifier
                        .padding(10.dp, 0.dp, 0.dp, 0.dp)
                        .size(14.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = Utils.getSensitivity(plant.sensitivity),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
