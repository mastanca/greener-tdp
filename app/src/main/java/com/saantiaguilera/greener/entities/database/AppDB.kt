package com.saantiaguilera.greener.entities.database

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.saantiaguilera.greener.entities.plant.Plant


class AppDB {

    companion object {

        fun getPlants(ctx: Context?): Array<Plant> {
            val json = ctx!!.getSharedPreferences("plants", 0).getString("plants", "[]")
            return Gson().fromJson(json, Array<Plant>::class.java)
        }

        fun addPlant(plant: Plant, ctx: Context?) {
            val plants = getPlants(ctx).toMutableList()
            plants.add(plant)
            val json = GsonBuilder().create().toJson(plants)
            ctx!!.getSharedPreferences("plants", 0).edit().putString("plants", json).apply()
        }

    }

}



