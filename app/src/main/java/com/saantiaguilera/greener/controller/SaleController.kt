package com.saantiaguilera.greener.controller

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.bluelinelabs.conductor.rxlifecycle2.RxController
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.adapter.plants.PlantsMenuAdapter
import com.saantiaguilera.greener.entities.database.AppDB
import com.saantiaguilera.greener.entities.plant.Plant

class SaleController : RxController() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (container.context as? AppCompatActivity)?.supportActionBar?.apply {
            title = "Add new sale"
            show()
        }

        return inflater.inflate(R.layout.controller_new_sale, container, false).apply {
            findViewById<RecyclerView>(R.id.choose_plant_recycler).apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = PlantsMenuAdapter().apply {
                    clickListener = { selectPlant(it) }
                    plants = AppDB.getPlants(context)
                }
            }
        }
    }

    private fun selectPlant(plant: Plant) {
        router.pushController(RouterTransaction.with(SaleDetailController().apply { this.plant = plant })
                .pushChangeHandler(HorizontalChangeHandler())
                .popChangeHandler(HorizontalChangeHandler()))

    }

}