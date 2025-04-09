package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun Home(navController: NavHostController){
    Column() {
        Row(){
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "logo",
                modifier = Modifier.width(200.dp)
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterVertically)
                    .height(50.dp))
            Image(painter = painterResource(id = R.drawable.profile),
                contentDescription = "profile-picture",
                modifier = Modifier.padding(10.dp)
                    .align(alignment = Alignment.CenterVertically)
                    .clickable { navController.navigate(Profile.route) }
                    .width(50.dp))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    Home(navController = NavHostController(LocalContext.current))
}