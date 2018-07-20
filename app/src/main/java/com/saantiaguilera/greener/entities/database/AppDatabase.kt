package com.saantiaguilera.greener.entities.database

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import com.saantiaguilera.greener.entities.plant.Plant
import com.saantiaguilera.greener.entities.plant.PlantDao


@Database(entities = arrayOf(Plant::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun plantDao(): PlantDao
}
