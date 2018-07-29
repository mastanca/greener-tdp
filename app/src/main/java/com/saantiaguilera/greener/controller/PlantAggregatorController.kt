package com.saantiaguilera.greener.controller

import android.app.ProgressDialog
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.bluelinelabs.conductor.rxlifecycle2.RxController
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.random
import com.saantiaguilera.greener.util.ResourcesUtil
import kotlinx.android.extensions.CacheImplementation
import kotlinx.android.extensions.ContainerOptions
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.controller_aggregator_plant.*


/**
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

            val data = container.context.resources.getStringArray(R.array.plants_arrays).toList()
            val dataAdapter = ArrayAdapter<String>(container.context,
                    android.R.layout.simple_spinner_item, data)
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            findViewById<Spinner>(R.id.inputSpinner).apply {
                adapter = dataAdapter
                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        inflate(null)
                    }

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        inflate(view!!.context.resources.getStringArray(R.array.plants_arrays)[position])
                    }
                }
            }
        }
    }

    private fun inflate(name: String?) {
        inputName.setText(name ?: "")
        if (name == null) {
            inputPlantImage.visibility = View.INVISIBLE
            inputWateringInterval.setText("")
            inputDialySunlightHours.setText("")
            inputTemperature.setText("")
        } else {
            inputPlantImage.visibility = View.VISIBLE
            inputPlantImage.setImageResource(ResourcesUtil.random())
            inputWateringInterval.setText((1..5).random().toString())
            inputDialySunlightHours.setText((1..14).random().toString())
            inputTemperature.setText((10..30).random().toString())
        }
    }

    private fun addPlant() {
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
        if (applicationContext != null) {

        }

        home()
    }

    private fun home() {
        router.setRoot(RouterTransaction.with(HomeController())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

}
