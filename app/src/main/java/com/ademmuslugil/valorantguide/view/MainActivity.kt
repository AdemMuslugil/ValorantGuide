package com.ademmuslugil.valorantguide.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ademmuslugil.valorantguide.SharedViewModel
import com.ademmuslugil.valorantguide.ui.theme.ValorantGuideTheme
import com.ademmuslugil.valorantguide.util.PrefManager
import com.ademmuslugil.valorantguide.view.agents.AgentsScreen
import com.ademmuslugil.valorantguide.view.agents.agentdetail.AgentDetailsScreen
import com.ademmuslugil.valorantguide.view.home.HomeScreen
import com.ademmuslugil.valorantguide.view.settings.SettingsScreen
import com.ademmuslugil.valorantguide.view.splash.SplashScreen
import com.ademmuslugil.valorantguide.view.weapons.WeaponsScreen
import com.ademmuslugil.valorantguide.view.weapons.weapondetails.WeaponDetailScreen
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ValorantGuideTheme {
                Navigation(this)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Glide.get(this).clearMemory()
    }
}

@Composable
fun Navigation(activity: MainActivity) {
    val sharedViewModel: SharedViewModel = hiltViewModel()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable(route = "splash_screen") {
            SplashScreen(navController = navController)
        }

        composable(route = "home_screen") {
            HomeScreen(navController = navController)
        }

        composable(route = "agent_screen") {
            AgentsScreen(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable("agent_detail_screen"){
            AgentDetailsScreen(navController = navController,sharedViewModel = sharedViewModel)
        }

        composable("weapons_screen") {
            WeaponsScreen(navController = navController)
        }

        composable("weapon_detail_screen/{id}") {
            val id = navController.currentBackStackEntry?.arguments?.getString("id")
            if (id!=null)
                WeaponDetailScreen(id = id, navController = navController)
        }

        composable("settings_screen") {
            SettingsScreen(navController = navController,activity)
        }

    }

}