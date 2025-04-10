package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.compose.runtime.observeAsState

@Composable
fun Home(navController: NavHostController) {
    val context = LocalContext.current
    val db = (context.applicationContext as MyApp).database
    val menuItems by db.menuItemDao().getAll().observeAsState(emptyList())

    Column {
        Row {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier
                    .width(200.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
                    .height(50.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "profile-picture",
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterVertically)
                    .clickable { navController.navigate(Profile.route) }
                    .width(50.dp)
            )
        }

        LazyColumn(
            modifier = Modifier.padding(10.dp),
            state = rememberLazyListState()
        ) {
            items(menuItems) { item ->
                ItemCard(item)
            }
        }
    }
}

@Composable
fun ItemCard(item: MenuItem) {
    Card(modifier = Modifier
        .padding(8.dp)
        .width(250.dp)) {
        Row(modifier = Modifier.padding(10.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = item.title)
                Text(text = item.description)
                Text(text = "${item.price} $")
            }

            AsyncImage(
                model = item.image,
                contentDescription = "food image",
                modifier = Modifier
                    .padding(start = 10.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun AsyncImage(model: Any, contentDescription: String, modifier: Any, contentScale: Any) {

}

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    Home(navController = NavHostController(LocalContext.current))
}