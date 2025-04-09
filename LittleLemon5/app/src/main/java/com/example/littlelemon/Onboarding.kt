package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun Onboarding() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    Column() {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
                .width(200.dp)
        )
        Box(
            modifier = Modifier
                .background(Color(0xFF495E57))
                .padding(16.dp)
                .fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            Text(text = "Welcome to Little Lemon", color = Color(0xFFEDEFEE))
        }
        Text(
            text = "Please enter your details",
            modifier = Modifier.padding(16.dp)
        )
        TextField(
            value = firstName,
            onValueChange = { newText -> firstName = newText },
            label = { Text("First Name") },
            modifier = Modifier.padding(16.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(10.dp)),
        )
        TextField(
            value = lastName,
            onValueChange = { newText -> lastName = newText },
            label = { Text("Last Name") },
            modifier = Modifier.padding(16.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(10.dp)))
        TextField(
            value = email,
            onValueChange = { newText -> email = newText },
            label = { Text("Email") },
            modifier = Modifier.padding(16.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(10.dp)))

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = buttonColors(Color(0xFFF4CE14))
        ) {
            Text(text = "Register", color = Color(0xFF333333))
        }
    }

}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview(){
    Onboarding()
}