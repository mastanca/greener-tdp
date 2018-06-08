package com.saantiaguilera.greener.controller

import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bluelinelabs.conductor.rxlifecycle2.RxController
import com.saantiaguilera.greener.R

/**
 * TODO Describe what this class do.
 */
class SingleProductShopController : RxController() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (container.context as? AppCompatActivity)?.supportActionBar?.apply {
            title = "Producto Testeo"
            show()
        }

        return inflater.inflate(R.layout.controller_product_shop, container, false).apply {
            findViewById<ImageView>(R.id.controller_product_shop_buy_view).setOnClickListener {
                Toast.makeText(context, "You have clicked the buy button", Toast.LENGTH_LONG).show()
            }
        }
    }

}