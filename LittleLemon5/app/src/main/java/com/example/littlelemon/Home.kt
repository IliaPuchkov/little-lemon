package com.example.littlelemon

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun Home(navController: NavHostController){
    Column() {
        Text(text = "Home")
        Button(onClick = {navController.navigate(Profile.route)}) {
            Text(text = "Profile")
        }
    }
}