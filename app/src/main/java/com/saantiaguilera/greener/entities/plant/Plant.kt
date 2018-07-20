package com.saantiaguilera.greener.entities.plant

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity
class Plant(@PrimaryKey var name : String,
            @ColumnInfo(name = "min_temp") var minTemp : Double,
            @ColumnInfo(name = "max_temp") var maxTemp : Double,
            @ColumnInfo(name = "sunlight_hours") var sunlightHours : Double,
            @ColumnInfo(name = "watering_interval") var watering_interval : Int)
