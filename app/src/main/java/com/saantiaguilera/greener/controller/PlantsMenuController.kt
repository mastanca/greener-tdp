package com.saantiaguilera.greener.controller

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.bluelinelabs.conductor.rxlifecycle2.RxController
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.adapter.plants.PlantsMenuAdapter
import com.saantiaguilera.greener.entities.database.AppDB
import com.saantiaguilera.greener.entities.plant.Plant
import kotlinx.android.extensions.LayoutContainer

class PlantsMenuController : RxController(), LayoutContainer {


    override val containerView: View?
        get() = view

    lateinit var recyclerView: RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (container.context as? AppCompatActivity)?.supportActionBar?.apply {
            title = "Elige un cultivo"
            show()
        }

        return inflater.inflate(R.layout.controller_plants_menu, container, false).apply {
            recyclerView = findViewById<RecyclerView>(R.id.controller_plants_menu_recycler_field).apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = PlantsMenuAdapter().apply {
                    clickListener = { selectPlant(it) }
                    plants = AppDB.getAllPlants(context)
                }
            }
        }
    }

    private fun selectPlant(plant: Plant) {
        router.pushController(RouterTransaction.with(PlantDescriptionController().apply { this.plant = plant })
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

}