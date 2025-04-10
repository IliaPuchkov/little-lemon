package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun Home(navController: NavHostController, menuDao: MenuDao) {
    Column(modifier = Modifier.fillMaxSize()) {
        val menuItems by menuDao.getAll().observeAsState(emptyList())
        Header(navController)
        HeroSection(menuItems)
    }
}

@Composable
fun Header(navController: NavHostController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = Modifier.padding(45.dp)) { }

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroSection(menuItems: List<MenuItemEntity>) {
    var searchPhrase by remember { mutableStateOf("") }
    var categoryFilter by remember { mutableStateOf("") }

    val filteredItems = menuItems.filter {
        it.title.contains(searchPhrase, ignoreCase = true) &&
                (categoryFilter.isBlank() || it.category == categoryFilter)
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color(0xFF495E57))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF495E57)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            ) {
                Text("Little Lemon", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color(0xFFF4CE14))
                Text("Chicago", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFFEDEFEE))
                Text(
                    "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                    fontSize = 16.sp,
                    color = Color(0xFFEDEFEE)
                )
            }

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

        TextField(
            value = searchPhrase,
            onValueChange = { newText -> searchPhrase = newText },
            label = { Text("Search") },
            placeholder = { Text("Enter search phrase") },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color(0xFF495E57),
                focusedTextColor = Color(0xFF495E57),
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
        )
    }

    val categories = menuItems.map { it.category }.distinct()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("ORDER FOR DELIVERY!", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF333333))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            categories.forEach { category ->
                MenuCategory(
                    category = category,
                    isSelected = categoryFilter == category,
                    onToggle = { clickedCategory ->
                        categoryFilter = if (categoryFilter == clickedCategory) "" else clickedCategory
                    }
                )
            }
        }
    }

    LazyColumn {
        items(filteredItems.size) { index ->
            MenuItemCard(filteredItems[index])
        }
    }
}

@Composable
fun MenuCategory(
    category: String,
    isSelected: Boolean,
    onToggle: (String) -> Unit
) {
    val backgroundColor = if (isSelected) Color(0xFFF4CE14) else Color.LightGray

    Box(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .clickable { onToggle(category) }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = category.uppercase(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
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
    Card(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text(item.title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF333333))
                Text(item.description, fontSize = 14.sp, color = Color(0xFF495E57))
                Text("Price: $${item.price}", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF495E57))
            }
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .width(150.dp)
                    .height(150.dp)
                    .align(Alignment.CenterVertically)
            ) {
                GlideImage(
                    model = item.image,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}