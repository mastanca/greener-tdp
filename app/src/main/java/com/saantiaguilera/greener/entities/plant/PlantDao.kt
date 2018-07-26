package com.saantiaguilera.greener.entities.plant

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query


@Dao
interface PlantDao {
    @get:Query("SELECT * FROM Plant")
    val all: List<Plant>

    @Query("SELECT * FROM Plant WHERE name LIKE :rename")
    fun findByName(rename: String): Plant

    @Insert
    fun insertAll(vararg Plants: Plant)

    @Delete
    fun delete(Plant: Plant)
}
