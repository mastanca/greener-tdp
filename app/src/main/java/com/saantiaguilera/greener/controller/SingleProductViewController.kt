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
 * TODO Describe what this class do.
 */
class SingleProductViewController : RxController() {

    var icon: Int = R.drawable.ic_splash
    var plant: Plant? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (container.context as? AppCompatActivity)?.supportActionBar?.apply {
            title = "Producto"
            show()
        }

        return inflater.inflate(R.layout.controller_product_view, container, false).apply {
            findViewById<ImageView>(R.id.product_view_ic_image).setImageResource(icon)
        }
    }

}