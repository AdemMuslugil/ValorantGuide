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
import com.ademmuslugil.valorantguide.model.AgentDetail
import com.ademmuslugil.valorantguide.ui.theme.ValorantGuideTheme
import com.ademmuslugil.valorantguide.view.agents.AgentsScreen
import com.ademmuslugil.valorantguide.view.agents.agentdetail.AgentDetailsScreen
import com.ademmuslugil.valorantguide.view.home.HomeScreen
import com.ademmuslugil.valorantguide.view.splash.SplashScreen
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ValorantGuideTheme {
                Navigation()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Glide.get(this).clearMemory()
    }
}

@Composable
fun Navigation() {
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
            val result = navController.currentBackStackEntry?.savedStateHandle?.get<AgentDetail>("agentDetail")
            AgentDetailsScreen(navController = navController,sharedViewModel = sharedViewModel)
        }
    }

}