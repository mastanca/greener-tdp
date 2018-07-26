package com.saantiaguilera.greener.util

import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.random

/**
 * Some class from the project
 */
class ResourcesUtil {

    companion object {

        private val possibles = arrayOf(
                R.drawable.albahaca,
                R.drawable.frutilla,
                R.drawable.lechuga,
                R.drawable.mandarinas,
                R.drawable.manzana_roja,
                R.drawable.manzana_verde,
                R.drawable.naranjas,
                R.drawable.palta,
                R.drawable.perejil,
                R.drawable.uvas,
                R.drawable.zanahoria,
                R.drawable.zapallo
        )

        fun random() = possibles.random()

    }

}

fun <T> Array<out T>.random(): T = get((0 until size).random())