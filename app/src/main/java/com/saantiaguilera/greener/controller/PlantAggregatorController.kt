package com.saantiaguilera.greener.controller

import android.app.ProgressDialog
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.bluelinelabs.conductor.rxlifecycle2.RxController
import com.saantiaguilera.greener.R
import kotlinx.android.extensions.CacheImplementation
import kotlinx.android.extensions.ContainerOptions
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.controller_aggregator_plant.*

/**
 * TODO HACER VALIDACIONES BIEN
 * TODO AGREGAR COSITOS AL SPINNER
 * TODO QUE EL SPINNER INFLE HARDCODEADO ALGUNOS VALORCITOS
 * TODO CREAR UN DTO CON LOS VALORCITOS PARA PORTO
 * Created by Manuel Porto
 */
@ContainerOptions(cache = CacheImplementation.NO_CACHE)
class PlantAggregatorController : RxController(), LayoutContainer {

    override val containerView: View?
        get() = view

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (container.context as? AppCompatActivity)?.supportActionBar?.apply {
            title = "Agregar un cultivo"
            show()
        }

        return inflater.inflate(R.layout.controller_aggregator_plant, container, false).apply {
            findViewById<View>(R.id.btnAdd).setOnClickListener { addPlant() }
        }
    }

    private fun addPlant() {
        if (!validate()) {
            onAddPlantFailed()
            return
        }

        btnAdd.isEnabled = false

        val progressDialog = ProgressDialog(containerView!!.context).apply {
            isIndeterminate = true
            setMessage("Planting crop...")
            show()
        }

        Handler().postDelayed({
            onAddPlantSuccess()
            progressDialog.dismiss()
        }, 3000)
    }


    private fun onAddPlantSuccess() {
        btnAdd.isEnabled = true
        home()
    }

    private fun onAddPlantFailed() {
        btnAdd.isEnabled = true
    }

    private fun home() {
        router.setRoot(RouterTransaction.with(HomeController())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

    private fun validate(): Boolean {
        var valid = true

        val name = inputName.text.toString()
        val temperature = inputTemperature.text.toString().toDouble()
        val dailySunlightHours = inputDialySunlightHours.text.toString().toDouble()
        val wateringInterval = inputWateringInterval.text.toString().toInt()

        if (name.isEmpty() || name.length < 3) {
            inputName.error = "at least 3 characters"
            valid = false
        } else {
            inputName.error = null
        }

        return valid
    }

}