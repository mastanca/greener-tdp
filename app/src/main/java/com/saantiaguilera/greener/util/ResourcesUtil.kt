package com.saantiaguilera.greener.util

import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.random

/**
 * Some class from the project
 */
class ResourcesUtil {

    companion object {

        private val possibles = arrayOf(
                R.drawable.albahaca,        // 0x7f07005a
                R.drawable.frutilla,        // 0x7f070066
                R.drawable.lechuga,         // 0x7f07007e
                R.drawable.mandarinas,      // 0x7f070080
                R.drawable.manzana_roja,    // 0x7f070081
                R.drawable.manzana_verde,   // 0x7f070082
                R.drawable.naranjas,        // 0x7f070083
                R.drawable.palta,           // 0x7f070093
                R.drawable.perejil,         // 0x7f070094
                R.drawable.uvas,            // 0x7f07009d
                R.drawable.zanahoria,       // 0x7f07009e
                R.drawable.zapallo          // 0x7f07009f
        )

        fun random() = possibles.random()

    }

}

fun <T> Array<out T>.random(): T = get((0 until size).random())