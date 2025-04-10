package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController, menuDao: MenuDao){
    val sharedPreferences = LocalContext.current.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    val isRegistered = sharedPreferences.getBoolean("isRegistered", false)

    NavHost(navController = navController, startDestination = if (isRegistered) Home.route else Onboarding.route){
        composable(Onboarding.route){
            Onboarding(navController)
        }
        composable(Home.route) {
            Home(navController, menuDao)
        }
        composable(Profile.route) {
            Profile(navController)
        }
    }
}