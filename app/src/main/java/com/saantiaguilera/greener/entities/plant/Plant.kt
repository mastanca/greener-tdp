package com.saantiaguilera.greener.entities.plant

import com.google.gson.annotations.SerializedName

class Plant(var name : String,
            @SerializedName("min_temp") var minTemp : Double,
            @SerializedName("max_temp") var maxTemp : Double,
            @SerializedName("sunlight_hours") var sunlightHours : Double,
            @SerializedName("watering_interval") var watering_interval : Int,
            @SerializedName("icon") var icon : String) {

    fun getIcon(): Int {
        return java.lang.Long.parseLong(icon.removePrefix("0x"), 16).toInt()
    }

}
