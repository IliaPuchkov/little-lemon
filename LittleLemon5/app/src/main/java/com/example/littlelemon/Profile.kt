package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Profile(navController: NavHostController){
    val sharedPreferences = LocalContext.current.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    val firstName = sharedPreferences.getString("firstName", "")
    val lastName = sharedPreferences.getString("lastName", "")
    val email = sharedPreferences.getString("email", "")

    fun logout(){
        navController.navigate(Onboarding.route)
        sharedPreferences.edit().clear().apply()
    }
    Column(modifier = Modifier.padding(30.dp)) {
        Button(onClick = {navController.navigate(Home.route)}) {
            Text(text = "Back")
        }
        Text(text = "Hello, $firstName!")
        Text(text = "First Name: $firstName")
        Text(text = "Last Name: $lastName")
        Text(text = "Email: $email")
        Button(onClick = {logout()}) {
            Text(text = "Logout")
        }
    }
}