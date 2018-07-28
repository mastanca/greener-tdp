package com.saantiaguilera.greener.controller

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bluelinelabs.conductor.rxlifecycle2.RxController
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.adapter.description.HistoryAdapter
import com.saantiaguilera.greener.entities.plant.Plant
import com.saantiaguilera.greener.model.History
import kotlinx.android.synthetic.main.controller_product_view.view.*
import kotlin.math.roundToInt

/**
 * Some class from the project
 */
class SingleProductViewController : RxController() {

    lateinit var plant: Plant

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (container.context as? AppCompatActivity)?.supportActionBar?.apply {
            title = plant.name
            show()
        }

        val waterInterval = plant.watering_interval
        val waterText = if (waterInterval > 1) String.format("Every %d days", waterInterval) else "Every day"
        val sunlightHours = plant.sunlightHours.roundToInt()
        val sunUnit = if (sunlightHours > 1) "hours" else "hour"
        val tempText = String.format("%.1f°C - %.1f°C", plant.minTemp, plant.maxTemp)

        return inflater.inflate(R.layout.controller_product_view, container, false).apply {
            wateringText.setText(waterText)
            sunlightText.setText(String.format("%d %s daily", sunlightHours, sunUnit))
            temperatureText.setText(tempText)
            findViewById<ImageView>(R.id.product_view_ic_image).setImageResource(plant.getIcon(context))
            findViewById<RecyclerView>(R.id.controller_product_view_history_recycler_view).apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = HistoryAdapter().apply {
                    history = arrayOf(
                            History("10/05/2018", "Plantada"),
                            History("11/05/2018", "Regada"),
                            History("25/05/2018", "Trasplantada"),
                            History("25/05/2018", "Regada")
                    )
                }
            }
        }
    }

}