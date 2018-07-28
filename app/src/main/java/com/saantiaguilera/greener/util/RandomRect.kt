package com.saantiaguilera.greener.util

import com.google.android.gms.maps.model.LatLng
import java.util.*

class RandomRect(val left: Double, val top: Double, val right: Double, val bottom: Double) {

    private val random = Random()

    fun next(): LatLng {
        val randomLat = bottom + (top - bottom) * random.nextDouble()
        val randomLng = left + (right - left) * random.nextDouble()
        return LatLng(randomLat, randomLng)
    }

}