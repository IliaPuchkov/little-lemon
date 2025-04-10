package com.example.littlelemon

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val client = HttpClient(Android){
        install(ContentNegotiation){
            json()
        }
    }


    private suspend fun getMenu(): List<MenuItemNetwork> {
        val response: MenuNetworkData = client
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body()
        return response.menu
    }

    private suspend fun saveToDatabase(menuItems: List<MenuItemNetwork>) {
        val entities = menuItems.map {
            MenuItem(
                id = it.id,
                title = it.title,
                description = it.description,
                price = it.price,
                image = it.image,
                category = it.category
            )
        }
        database.menuItemDao().insertAll(entities)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        initDatabase(applicationContext)
        lifecycleScope.launch {
            if (database.menuItemDao().getAll().isEmpty()) {
                saveToDatabase(getMenu())
            }
        }
        setContent {
            LittleLemonTheme {


                MyNavigation()
            }
        }
    }
}

@Composable
fun MyNavigation() {
    val navController = rememberNavController()
    Navigation(navController = navController)
}