package com.saantiaguilera.greener.entities.database

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.saantiaguilera.greener.entities.plant.Plant
import com.saantiaguilera.greener.model.Sale


class AppDB {

    companion object {

        fun getPlants(ctx: Context?): Array<Plant> {
            val json = ctx!!.getSharedPreferences("plants.json", 0).getString("plants.json", "[]")
            return Gson().fromJson(json, Array<Plant>::class.java)
        }

        fun addPlant(plant: Plant, ctx: Context?) {
            val plants = getPlants(ctx).toMutableList()
            plants.add(plant)
            val json = GsonBuilder().create().toJson(plants)
            ctx!!.getSharedPreferences("plants.json", 0).edit().putString("plants.json", json).apply()
        }

        fun getAllPlants(ctx: Context?): Array<Plant> {
            val json = ctx!!.assets.open("plants.json").bufferedReader().use {
                it.readText()
            }
            return Gson().fromJson(json, Array<Plant>::class.java)
        }

        fun getSales(ctx: Context?): Array<Sale> {
            val json = ctx!!.getSharedPreferences("sales.json", 0).getString("sales.json", "[]")
            return Gson().fromJson(json, Array<Sale>::class.java)
        }

        fun addSale(sale: Sale, ctx: Context?) {
            val sales = getSales(ctx).toMutableList()
            sales.add(sale)
            val json = GsonBuilder().create().toJson(sales)
            ctx!!.getSharedPreferences("sales.json", 0).edit().putString("sales.json", json).apply()
        }

        fun removeSale(position: Int, ctx: Context?) {
            val sales = getSales(ctx).toMutableList()
            sales.removeAt(position)
            val json = GsonBuilder().create().toJson(sales)
            ctx!!.getSharedPreferences("sales.json", 0).edit().putString("sales.json", json).apply()
        }
    }

}



