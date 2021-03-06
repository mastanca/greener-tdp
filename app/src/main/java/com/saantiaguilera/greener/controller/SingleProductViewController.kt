package com.saantiaguilera.greener.controller

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ImageView
import com.bluelinelabs.conductor.rxlifecycle2.RxController
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.adapter.description.HistoryAdapter
import com.saantiaguilera.greener.entities.database.AppDB
import com.saantiaguilera.greener.entities.plant.Plant
import com.saantiaguilera.greener.model.History
import kotlinx.android.synthetic.main.controller_product_view.view.*
import java.util.*
import kotlin.math.roundToInt

/**
 * Some class from the project
 */
class SingleProductViewController : RxController() {

    lateinit var plant: Plant
    var showHistory: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (container.context as? AppCompatActivity)?.supportActionBar?.apply {
            title = plant.name
            show()
        }

        setHasOptionsMenu(true)

        val waterInterval = plant.watering_interval
        val waterText = if (waterInterval > 1) String.format("Cada %d dias", waterInterval) else "Cada día"
        val sunlightHours = plant.sunlightHours.roundToInt()
        val sunUnit = if (sunlightHours > 1) "horas" else "hora"
        val tempRangeText = String.format("%.1f°C - %.1f°C", plant.minTemp, plant.maxTemp)
        val tempText = String.format("%.1f °C", Random().nextDouble()*(plant.maxTemp-plant.minTemp)+plant.minTemp)

        return inflater.inflate(R.layout.controller_product_view, container, false).apply {
            findViewById<ImageView>(R.id.product_view_ic_image).setImageResource(plant.getIcon(context))
            humidityView.setHumidity(Random().nextDouble())
            temperatureText.text = tempText
            luminosityText.text = String.format("%d lm", (Random().nextDouble()*(1000)+250).toInt())

            wateringText.setText(waterText)
            sunlightText.setText(String.format("%d %s por día", sunlightHours, sunUnit))
            temperatureRangeText.setText(tempRangeText)

            if (!showHistory) findViewById<View>(R.id.product_view_history_title).visibility = View.GONE
            findViewById<RecyclerView>(R.id.controller_product_view_history_recycler_view).apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = HistoryAdapter().apply {
                    history = if (showHistory) arrayOf(
                            History("10/05/2018", "Plantada"),
                            History("11/05/2018", "Regada"),
                            History("25/05/2018", "Trasplantada"),
                            History("25/05/2018", "Regada")
                    ) else arrayOf()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.plant_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_delete -> {
            deletePlant()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun deletePlant() {
        val builder = AlertDialog.Builder(activity, R.style.AppAlertDialog)
        builder.setMessage("¿Está seguro que quiere eliminar la planta?")
                .setTitle("ATENCIÓN")

        builder.setPositiveButton("Si") { dialog, _ ->
            dialog.dismiss()
            AppDB.removePlant(plant, applicationContext)
            router.popCurrentController()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}