package com.saantiaguilera.greener.controller

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.rxlifecycle2.RxController
import com.saantiaguilera.greener.R

/**
 * TODO Describe what this class do.
 */
class SingleProductShopController : RxController() {

    override fun onContextAvailable(context: Context) {
        super.onContextAvailable(context)
        (context as? AppCompatActivity)?.supportActionBar?.apply {
            title = "Producto Testeo"
            show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View =
            inflater.inflate(R.layout.controller_product_shop, container, false)

}