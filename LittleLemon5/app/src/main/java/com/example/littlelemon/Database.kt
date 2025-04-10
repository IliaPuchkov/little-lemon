package com.example.littlelemon

import android.view.MenuItem
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Database
(entities = [MenuItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun menuItemDao(): MenuItemDao
}

@Dao
interface MenuItemDao {
    @Insert
    fun insertAll(items: List<com.example.littlelemon.MenuItem>)

    @Query("SELECT * FROM menuItemsTable")
    fun getAll(): List<MenuItem>
}

@Entity(tableName = "menuItemsTable")
data class MenuItem(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
)