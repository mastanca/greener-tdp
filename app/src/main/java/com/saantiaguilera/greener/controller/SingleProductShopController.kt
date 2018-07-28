package com.saantiaguilera.greener.controller

import android.app.ProgressDialog
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.bluelinelabs.conductor.rxlifecycle2.RxController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.entities.plant.Plant
import com.saantiaguilera.greener.view.GreenerMapFragment
import kotlinx.android.extensions.LayoutContainer

/**
 * Some class from the project
 */
class SingleProductShopController : RxController(), LayoutContainer {

    override val containerView: View?
        get() = view

    lateinit var plant: Plant

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (container.context as? AppCompatActivity)?.supportActionBar?.apply {
            title = plant.name
            show()
        }

        return inflater.inflate(R.layout.controller_single_product_shop, container, false).apply {
            findViewById<ImageView>(R.id.singleProductShopImage).setImageResource(plant.getIcon(context))
            findViewById<ImageView>(R.id.singleProductShopBuyView).setOnClickListener {
                val progressDialog = ProgressDialog(it.context, R.style.AppAlertDialog).apply {
                    isIndeterminate = true
                    setMessage("Comprando producto...")
                    show()
                }
                Handler().postDelayed({
                    onBuySuccess()
                    progressDialog.dismiss()
                }, 1000)
            }
            ((context as AppCompatActivity).supportFragmentManager.findFragmentById(R.id.singleProductShopMap) as GreenerMapFragment).getMapAsync { map ->
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-34.588653, -58.454734), 11.5f))
                map.isMyLocationEnabled = true
            }
        }
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        val supportFragment = (view.context as AppCompatActivity).supportFragmentManager
        supportFragment.beginTransaction().remove(supportFragment.findFragmentById(R.id.singleProductShopMap)).commitNowAllowingStateLoss()
    }

    private fun onBuySuccess() {
        router.setRoot(RouterTransaction.with(HomeController())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

}