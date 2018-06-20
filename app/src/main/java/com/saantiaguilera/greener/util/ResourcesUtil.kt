package com.saantiaguilera.greener.util

import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.random

/**
 * TODO Describe what this class do.
 */
class ResourcesUtil {

    companion object {

        private val possibles = arrayOf(
                R.drawable.icons8_asparagus_vegetable,
                R.drawable.icons8_aubergine,
                R.drawable.icons8_avocado,
                R.drawable.icons8_beetroot,
                R.drawable.icons8_big_carrot,
                R.drawable.icons8_button_mushroom,
                R.drawable.icons8_croque_monsieur,
                R.drawable.icons8_green_cucumber,
                R.drawable.icons8_green_lettuce,
                R.drawable.icons8_onion_bulb,
                R.drawable.icons8_pear,
                R.drawable.icons8_radish_vegetable,
                R.drawable.icons8_red_tomato,
                R.drawable.icons8_white_cauliflower
        )

        fun random() = possibles.random()

    }

}

fun <T> Array<out T>.random(): T = get((0 until size).random())