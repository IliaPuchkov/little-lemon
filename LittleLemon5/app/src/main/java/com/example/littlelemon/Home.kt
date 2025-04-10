package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage


@Composable
fun Home(navController: NavHostController, menuDao: MenuDao) {
    Column(modifier = Modifier.fillMaxSize()) {
        Header(navController)
        HeroSection()
        val menuItems by menuDao.getAll().observeAsState(emptyList())

        LazyColumn{
            item { MenuItems(menuItems) }
        }
    }
}



@Composable
fun Header(navController: NavHostController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = Modifier.padding(45.dp)){

        }
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .weight(1f)
                .height(80.dp)
                .padding(vertical = 20.dp)
                .align(Alignment.CenterVertically)
        )

        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile",
            modifier = Modifier
                .padding(20.dp)
                .width(50.dp)
                .align(Alignment.CenterVertically)
                .clickable { navController.navigate(Profile.route) }
        )
    }
}

@Composable
fun HeroSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF495E57)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically // Выравнивание по вертикали
    ) {
        // Текстовая часть
        Column(
            modifier = Modifier
                .padding(16.dp)
                .weight(1f) // Используем weight, чтобы колонка занимала доступное пространство
        ) {
            Text("Little Lemon", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color(0xFFF4CE14))
            Text("Chicago", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFFEDEFEE))
            Text(
                "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                fontSize = 16.sp,
                color = Color(0xFFEDEFEE)
            )
        }

        // Блок с изображением
        Image(
            painter = painterResource(id = R.drawable.hero_image),
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
                .width(150.dp)
                .height(150.dp)
                .fillMaxSize()
        )
    }
}


@Composable
fun MenuItems(menuItems: List<MenuItemEntity>) {
    Column {
        menuItems.forEach { item ->
            MenuItemCard(item)
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItemCard(item: MenuItemEntity) {
    Card(modifier = Modifier.padding(8.dp)
        .background(Color.White)) {
        Row(modifier = Modifier.fillMaxWidth().background(Color.White)) {
            Column(modifier = Modifier.padding(8.dp).weight(1f)) {
                Text(item.title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF333333))
                Text(item.description, fontSize = 14.sp, color = Color(0xFF495E57))
                Text("Price: $${item.price}", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF495E57))
            }
            Box(modifier = Modifier.padding(8.dp)
                .width(150.dp)
                .height(150.dp)
                .align(Alignment.CenterVertically)){
                GlideImage(
                    model = item.image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

        }
    }
}