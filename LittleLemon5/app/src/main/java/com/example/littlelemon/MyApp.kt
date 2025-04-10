package com.example.littlelemon

import android.app.Application
import androidx.room.Room

class MyApp : Application() {
    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "menu_db"
        ).build()
    }
}