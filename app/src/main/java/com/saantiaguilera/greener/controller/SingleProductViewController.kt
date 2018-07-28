package com.saantiaguilera.greener.controller

import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bluelinelabs.conductor.rxlifecycle2.RxController
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.entities.plant.Plant

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

        return inflater.inflate(R.layout.controller_product_view, container, false).apply {
            findViewById<ImageView>(R.id.product_view_ic_image).setImageResource(plant.getIcon(context))
        }
    }

}