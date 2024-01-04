package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.models.BeerEntity

@Database(entities = [BeerEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun beerDao(): BeerDao
}