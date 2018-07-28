package com.saantiaguilera.greener.model

import android.content.Context

data class Sale(val name: String,
                val description: String,
                val quantity: Int,
                val price: Int,
                val icon: String) {

    fun getIcon(ctx: Context): Int {
        return ctx.resources.getIdentifier(icon, "drawable", ctx.packageName)
    }
}