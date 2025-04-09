package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Row(){
            Button(onClick = {navController.navigate(Home.route)},
                colors = ButtonDefaults.buttonColors(Color(0xFFF4CE14))) {
                Text(text = "Back",
                    color = Color(0xFF333333))
            }
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "logo", 
                modifier = Modifier.width(200.dp)
                    .align(alignment = Alignment.CenterVertically)
                    .height(50.dp))
        }


        Text(text = "Profile Information:", modifier = Modifier.padding(bottom = 30.dp, top = 30.dp), fontSize = 20.sp)
        Text(text = "First Name: $firstName", modifier = Modifier.padding(bottom = 10.dp))
        Text(text = "Last Name: $lastName", modifier = Modifier.padding(bottom = 10.dp))
        Text(text = "Email: $email", modifier = Modifier.padding(bottom = 10.dp))
        Button(onClick = {logout()},
            modifier = Modifier.padding(10.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFF4CE14))) {
            Text(text = "Logout",
                color = Color(0xFF333333))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfilePreview(){
    Profile(navController = NavHostController(LocalContext.current))
}