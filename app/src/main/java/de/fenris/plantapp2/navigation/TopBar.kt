package de.fenris.plantapp2.navigation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import de.fenris.plantapp2.R
import de.fenris.plantapp2.screens.SettingsScreen
import de.fenris.plantapp2.ui.theme.getNavColor
import de.fenris.plantapp2.ui.theme.getOnSurfaceVariant
import de.fenris.plantapp2.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    closeIcon: Boolean = false,
    onDismissRequest: () -> Unit = {},
    appViewModel: AppViewModel = viewModel()
) {
    val appUiState by appViewModel.uiState.collectAsState()

TopAppBar(
    title = {
        Text(
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight(400),
            color = getOnSurfaceVariant(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    },
    actions = {
        if (closeIcon) {
            IconButton(
                onClick = {
                    onDismissRequest()
                }) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "Close Dialog"
                )
            }
        } else {
            IconButton(
                onClick = {
                    appViewModel.openSettings()
                    //showSettings = true
                }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Open Options",
                    tint = getOnSurfaceVariant()
                )
            }
        }
    },
    colors = TopAppBarDefaults.smallTopAppBarColors(
        containerColor = getNavColor(),
        titleContentColor = getOnSurfaceVariant()
    ),
)
if (appUiState.isSettingsOpen) {
    Dialog(
        onDismissRequest = {
            appViewModel.closeSettings()
            //showSettings = false
                           },
        properties = DialogProperties(
            dismissOnBackPress = true,
            usePlatformDefaultWidth = false
        ),

        ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.settings_title),
                            modifier = Modifier.fillMaxWidth(),
                            fontWeight = getWeight()
                        )
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                appViewModel.closeSettings()
                                //showSettings = false
                            }) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "Close Dialog"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = getNavColor(),
                        titleContentColor = getOnSurfaceVariant()
                    ),
                )
            },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        ) {
            Box(Modifier.padding(it)) {
                SettingsScreen()
            }
        }
    }
}
}

@Composable
fun getWeight(): FontWeight {
return if (isSystemInDarkTheme()) {
    FontWeight.Normal
} else {
    FontWeight.Bold
}
}