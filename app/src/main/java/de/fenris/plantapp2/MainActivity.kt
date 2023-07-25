package de.fenris.plantapp2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.fenris.plantapp2.viewmodel.TaskViewModel
import de.fenris.plantapp2.navigation.BottomNavigationBar
import de.fenris.plantapp2.navigation.NavigationBar
import de.fenris.plantapp2.navigation.TopNavBar
import de.fenris.plantapp2.navigation.TopNavigationBar
import de.fenris.plantapp2.screens.*
import de.fenris.plantapp2.ui.theme.PlantApp2Theme
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlantApp2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreenView()
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreenView(taskViewModel: TaskViewModel = hiltViewModel()){
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
                },
        topBar = {
            TopNavigationBar(navController = navController)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = NavigationBar.Home.route,
            modifier = Modifier.padding(it)
        ) {
            composable(TopNavBar.Home.route) { HomeScreen() }
            composable(TopNavBar.List.route) { ListScreen() }
            composable(TopNavBar.Task.route) { TaskScreen(viewModel = taskViewModel) }
            composable(TopNavBar.Calendar.route) { CalendarScreen() }

            composable(NavigationBar.Home.route) { HomeScreen() }
            composable(NavigationBar.List.route) { ListScreen() }
            composable(NavigationBar.Task.route) { TaskScreen(viewModel = taskViewModel) }
            composable(NavigationBar.Calendar.route) { CalendarScreen() }
        }
    }
    BackHandler(onBack = {
        navController.popBackStack()
    })
}
