package de.fenris.plantapp2.screens

import android.view.inputmethod.InputMethodManager
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
import kotlin.collections.ArrayList

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.*
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.platform.LocalContext
import de.fenris.plantapp2.ui.theme.getSurfaceContainer
import de.fenris.plantapp2.ui.theme.getTextFieldBackground

@Composable
fun ListScreen() {
    Box(Modifier.padding(10.dp, 10.dp, 10.dp, 10.dp)) {
        Utils.GetBackgroundImage()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.TopCenter)
        ) {
            val roomState = remember { mutableStateOf(Room.ALL_ROOMS) }
            SearchView(roomState)
            FilterPlantList(room = roomState)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(chosen: MutableState<Room>) {
    val listItems: List<String> = Room.values().map { Utils.getRoom(it) }.toList()
    val context = LocalContext.current
    var selectedItemIndex by remember { mutableIntStateOf(0) }
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
                .menuAnchor()
                .onFocusChanged {
                    if (it.isFocused) {
                        context
                            .findActivity()
                            ?.let { activity ->
                                val inputMethodManager =
                                    activity.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager
                                inputMethodManager.hideSoftInputFromWindow(
                                    activity.currentFocus?.windowToken,
                                    0
                                )
                            }
                    }
                },
            label = {
                Text(
                    stringResource(R.string.selected_room),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                        .compositeOver(MaterialTheme.colorScheme.onSurfaceVariant)
                )
            },
            value = listItems[selectedItemIndex],
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
                //trailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
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
                            fontWeight = if (index == selectedItemIndex) FontWeight.Bold else null
                        )
                    },
                    onClick = {
                        selectedItemIndex = index
                        expanded = false
                        chosen.value = Room.values()[index]
                    },
                )
            }
        }
    }
}

@Composable
fun FilterPlantList(room: MutableState<Room>) {
    val plantList = PlantList.instance
    if (plantList.allPlants.isEmpty()) {
        plantList.setData()
    }
    val list = plantList.allPlants.sortedBy { it.name }.toMutableList()
    var filteredPlants: List<Plant>

    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp, 0.dp, 0.dp, 0.dp)) {
        val selectedRoom = room.value

        filteredPlants = if (selectedRoom == Room.ALL_ROOMS) {
            list
        } else {
            val resultList = ArrayList<Plant>()
            for (plant in list) {
                if (plant.room.contains(selectedRoom)
                ) {
                    resultList.add(plant)
                }
            }
            resultList
        }

        items(filteredPlants) { plant ->
            GetCells4Plants(plant)
            Divider()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetCells4Plants(plant: Plant) {
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
                        title = stringResource(plant.name),
                        closeIcon = true,
                        onDismissRequest = { showDialog = false }
                    )
                },
            ) {
                Box(Modifier.padding(it)) {
                    DetailsScreen(plant)
                }
            }
        }
    }

    Row(Modifier.clickable(
        onClick = { showDialog = true }
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
                modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp),
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
                    //painter = painterResource(id = R.drawable.ic_water_drop),
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
                    //painter = painterResource(id = R.drawable.ic_sticky_note),
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
                    //painter = painterResource(id = R.drawable.ic_shield_filled),
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
