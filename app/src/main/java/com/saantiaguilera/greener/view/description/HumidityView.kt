package com.saantiaguilera.greener.view.description

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.saantiaguilera.greener.R


class HumidityView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var levelLights: Array<ImageView>

    init {
        LayoutInflater.from(context)
                .inflate(R.layout.view_humidity, this, true)
        orientation = HORIZONTAL
        levelLights = arrayOf(
            findViewById(R.id.view_humidity_level_1),
            findViewById(R.id.view_humidity_level_2),
            findViewById(R.id.view_humidity_level_3),
            findViewById(R.id.view_humidity_level_4),
            findViewById(R.id.view_humidity_level_5),
            findViewById(R.id.view_humidity_level_6)
        )
    }


    fun setHumidity(value: Double) {
        val percentage = (value*100).toInt()
        findViewById<TextView>(R.id.view_humidity_text).text = String.format("%d%%", percentage)
        when(percentage) {
            in (0..16) -> {
                levelLights[0].setImageResource(R.drawable.rounded_square_red)
                for (light in levelLights.copyOfRange(1,6)) {
                    light.setImageResource(R.drawable.rounded_square_grey)
                }
            }
            in (16..33) -> {
                for (light in levelLights.copyOfRange(0,2)) {
                    light.setImageResource(R.drawable.rounded_square_yellow)
                }
                for (light in levelLights.copyOfRange(2,6)) {
                    light.setImageResource(R.drawable.rounded_square_grey)
                }
            }
            in (33..50) -> {
                for (light in levelLights.copyOfRange(0,3)) {
                    light.setImageResource(R.drawable.rounded_square_green)
                }
                for (light in levelLights.copyOfRange(3,6)) {
                    light.setImageResource(R.drawable.rounded_square_grey)
                }
            }
            in (50..66) -> {
                for (light in levelLights.copyOfRange(0,4)) {
                    light.setImageResource(R.drawable.rounded_square_green)
                }
                for (light in levelLights.copyOfRange(3,6)) {
                    light.setImageResource(R.drawable.rounded_square_grey)
                }
            }
            in (66..84) -> {
                for (light in levelLights.copyOfRange(0,5)) {
                    light.setImageResource(R.drawable.rounded_square_blue)
                }
                levelLights[5].setImageResource(R.drawable.rounded_square_grey)
            }
            in (84..100) -> {
                for (light in levelLights) {
                    light.setImageResource(R.drawable.rounded_square_blue)
                }
            }
        }
    }

}