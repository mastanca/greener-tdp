package com.saantiaguilera.greener.controller

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.bluelinelabs.conductor.rxlifecycle2.RxController
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.adapter.home.MostSoldAdapter
import com.saantiaguilera.greener.adapter.home.PlantsAdapter
import com.saantiaguilera.greener.adapter.home.ProductsAdapter

/**
 * TODO Describe what this class do.
 */
class HomeController : RxController() {

    override fun onContextAvailable(context: Context) {
        super.onContextAvailable(context)
        (context as? AppCompatActivity)?.supportActionBar?.apply {
            title = resources!!.getString(R.string.app_name)
            show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_home, container, false)
        view.findViewById<RecyclerView>(R.id.controller_home_recycler_view_my_plants).apply {
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            adapter = PlantsAdapter().apply {
                addClickListener = { showSearch() }
                itemClickListener = View.OnClickListener { showDetailsForProduct() }
            }
        }
        view.findViewById<RecyclerView>(R.id.controller_home_recycler_view_my_products).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = ProductsAdapter().apply {
                clickListener = View.OnClickListener { showDetailsForProduct() }
            }
        }
        view.findViewById<RecyclerView>(R.id.controller_home_recycler_view_most_sold).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = MostSoldAdapter().apply {
                clickListener = View.OnClickListener { showShopForProduct() }
            }
        }
        return view
    }

    private fun showDetailsForProduct() {
        router.pushController(RouterTransaction.with(SingleProductViewController())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

    private fun showSearch() {

    }

    private fun showShopForProduct() {
        router.pushController(RouterTransaction.with(SingleProductShopController())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

}