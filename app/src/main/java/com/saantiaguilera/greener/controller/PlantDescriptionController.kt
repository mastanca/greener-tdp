package com.saantiaguilera.greener.controller

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.bluelinelabs.conductor.rxlifecycle2.RxController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.maps.android.heatmaps.Gradient
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.google.maps.android.heatmaps.WeightedLatLng
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.entities.database.AppDB
import com.saantiaguilera.greener.entities.plant.Plant
import com.saantiaguilera.greener.random
import com.saantiaguilera.greener.util.RandomRect
import com.saantiaguilera.greener.view.GreenerMapFragment
import kotlinx.android.extensions.LayoutContainer
import kotlin.coroutines.experimental.coroutineContext

/**
 * TODO Describe what this class do.
 */
class PlantDescriptionController : RxController(), LayoutContainer {

    override val containerView: View?
        get() = view

    lateinit var plant: Plant

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (container.context as? AppCompatActivity)?.supportActionBar?.apply {
            title = plant.name
            show()
        }

        return inflater.inflate(R.layout.controller_product_shop, container, false).apply {
            findViewById<ImageView>(R.id.product_shop_image).setImageResource(plant.getIcon(context))
            findViewById<ImageView>(R.id.controller_product_shop_buy_view).setOnClickListener {
//                showPlantNameDialog(it.context)
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
            ((context as AppCompatActivity).supportFragmentManager.findFragmentById(R.id.productShopMap) as GreenerMapFragment).getMapAsync { map ->
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-34.588653, -58.454734), 11.5f))
                map.isMyLocationEnabled = true
                addHeatMap(map)
            }
        }
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        val supportFragment = (view.context as AppCompatActivity).supportFragmentManager
        supportFragment.beginTransaction().remove(supportFragment.findFragmentById(R.id.productShopMap)).commitNowAllowingStateLoss()
    }

    private fun addHeatMap(map: GoogleMap) {
        val list = ArrayList<WeightedLatLng>()
        val randomRect = RandomRect(-58.519965, -34.535500, -58.365470, -34.640643)

        (1..50).forEach { list.add(WeightedLatLng(randomRect.next(), (100..300).random().toDouble())) }

        val colors = intArrayOf(
                Color.rgb(102, 225, 0),
                Color.rgb(255, 0, 0)
        )

        val startPoints = floatArrayOf(0.2f, 1f)

        val provider = HeatmapTileProvider.Builder()
                .weightedData(list)
                .gradient(Gradient(colors, startPoints))
                .opacity(0.7)
                .build()

        map.addTileOverlay(TileOverlayOptions().tileProvider(provider))
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

    private fun showPlantNameDialog(ctx: Context) {
        val builder = AlertDialog.Builder(applicationContext)
        builder.setTitle("Nombra tu planta")
        val plantAlias = EditText(applicationContext)
        builder.setView(plantAlias)

        builder.setPositiveButton(android.R.string.ok) { dialog, p1 ->
            plant.alias = plantAlias.text.toString()
            val progressDialog = ProgressDialog(ctx, R.style.AppAlertDialog).apply {
                isIndeterminate = true
                setMessage("Agregando cultivo...")
                show()
            }
            Handler().postDelayed({
                onAddPlantSuccess()
                progressDialog.dismiss()
            }, 1000)
        }
        builder.setNegativeButton("Cancelar") { dialog, p1 ->
            dialog.cancel()
        }

        builder.show()
    }

}
