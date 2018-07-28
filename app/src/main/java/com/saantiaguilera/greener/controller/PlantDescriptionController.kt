package com.saantiaguilera.greener.controller

import android.app.ProgressDialog
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.bluelinelabs.conductor.rxlifecycle2.RxController
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.entities.database.AppDB
import com.saantiaguilera.greener.entities.plant.Plant
import kotlin.coroutines.experimental.coroutineContext

/**
 * TODO Describe what this class do.
 */
class PlantDescriptionController : RxController() {

    lateinit var plant: Plant

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (container.context as? AppCompatActivity)?.supportActionBar?.apply {
            title = plant.name
            show()
        }

        return inflater.inflate(R.layout.controller_product_shop, container, false).apply {
            findViewById<ImageView>(R.id.product_shop_image).setImageResource(plant.getIcon(context))
            findViewById<ImageView>(R.id.controller_product_shop_buy_view).setOnClickListener {
                val progressDialog = ProgressDialog(it.context, R.style.AppAlertDialog).apply {
                    isIndeterminate = true
                    setMessage("Agregando cultivo...")
                    show()
                }
                Handler().postDelayed({
                    onAddPlantSuccess()
                    progressDialog.dismiss()
                }, 1000)
            }
        }
    }

    private fun onAddPlantSuccess() {
        AppDB.addPlant(plant, applicationContext)
        home()
    }

    private fun home() {
        router.setRoot(RouterTransaction.with(HomeController())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

}