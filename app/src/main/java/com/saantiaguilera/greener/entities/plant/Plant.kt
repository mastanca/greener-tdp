package com.saantiaguilera.greener.entities.plant

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.saantiaguilera.greener.util.ResourcesUtil


@Entity
class Plant(@PrimaryKey var name : String,
            @ColumnInfo(name = "min_temp") @SerializedName("min_temp") var minTemp : Double,
            @ColumnInfo(name = "max_temp") @SerializedName("max_temp") var maxTemp : Double,
            @ColumnInfo(name = "sunlight_hours") @SerializedName("sunlight_hours") var sunlightHours : Double,
            @ColumnInfo(name = "watering_interval") @SerializedName("watering_interval") var watering_interval : Int,
            @ColumnInfo(name = "icon") @SerializedName("icon") var icon : String) {

    fun getIcon(): Int {
        return java.lang.Long.parseLong(icon.removePrefix("0x"), 16).toInt()
    }

}
