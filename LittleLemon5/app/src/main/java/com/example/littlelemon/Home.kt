package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun Home(navController: NavHostController, menuDao: MenuDao) {
    Column(modifier = Modifier.fillMaxSize()) {
        Header()
        val menuItems by menuDao.getAll().observeAsState(emptyList())

        LazyColumn {
            item { HeroSection() }
            item { MenuItems(menuItems) }
        }
    }
}
//@Preview(showBackground = true)
//@Composable
//fun HomePreview() {
//    Home(navController = NavHostController(LocalContext.current), menuDao = MenuDao())
//}

@Composable
fun Header() {
    Row(){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile",
            modifier = Modifier
                .padding(vertical = 20.dp)
                .width(40.dp))
    }

}

@Composable
fun HeroSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Little Lemon", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Text("Chicago", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        Text(
            "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
            fontSize = 16.sp
        )
        Image(
            painter = painterResource(id = R.drawable.hero_image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
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
    Card(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(item.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(item.description, fontSize = 14.sp)
            Text("Price: $${item.price}", fontSize = 16.sp)
            GlideImage(
                model = item.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
        }
    }
}