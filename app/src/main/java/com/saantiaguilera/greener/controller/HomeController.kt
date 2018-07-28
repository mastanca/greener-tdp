package com.saantiaguilera.greener.controller

import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Toast
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler
import com.bluelinelabs.conductor.rxlifecycle2.RxController
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.adapter.home.MostSoldAdapter
import com.saantiaguilera.greener.adapter.home.PlantsAdapter
import com.saantiaguilera.greener.adapter.home.ProductsAdapter
import com.saantiaguilera.greener.entities.database.AppDB
import com.saantiaguilera.greener.entities.plant.Plant

/**
 * Some class from the project
 */
class HomeController : RxController() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (container.context as? AppCompatActivity)?.supportActionBar?.apply {
            title = resources!!.getString(R.string.app_name)
            show()
        }

        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.controller_home, container, false).apply {
            findViewById<RecyclerView>(R.id.controller_home_recycler_view_my_plants).apply {
                layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
                adapter = PlantsAdapter().apply {
                    plants = AppDB.getPlants(context)
                    addClickListener = { showAddPlantTab() }
                    itemClickListener = { showDetailsForProduct(it) }
                }
            }
            findViewById<RecyclerView>(R.id.controller_home_recycler_view_my_products).apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = ProductsAdapter().apply {
                    clickListener = { /*showDetailsForProduct(it)*/ }
                }
            }
            findViewById<RecyclerView>(R.id.controller_home_recycler_view_most_sold).apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = MostSoldAdapter().apply {
                    clickListener = { showShopForProduct(it) }
                }
            }
            findViewById<BottomNavigationView>(R.id.bottom_navigation).apply {
                setOnNavigationItemSelectedListener { item -> showTab(item) }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.app_bar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_notifications -> {
            showNotifications()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun showTab(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_home -> showHomeTab()
            R.id.action_store -> showStoreTab()
            R.id.action_profile -> showProfileTab()
        }
        return true
    }

    private fun showHomeTab() {
        // We are here
    }

    private fun showAddAPlantWizard() {
        router.pushController(RouterTransaction.with(PlantAggregatorController())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

    private fun showStoreTab() {
        router.setRoot(RouterTransaction.with(MarketController()))
    }

    private fun showProfileTab() {
        Toast.makeText(applicationContext, "Profile tab", Toast.LENGTH_LONG).show()
    }

    private fun showAddPlantTab() {
        router.pushController(RouterTransaction.with(PlantsMenuController())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

    private fun showDetailsForProduct(plant: Plant) {
        router.pushController(RouterTransaction.with(SingleProductViewController().apply { this.plant = plant })
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

    private fun showSearch() {
        router.pushController(RouterTransaction.with(SearchController())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

    private fun showShopForProduct(plant: Plant) {
//        router.pushController(RouterTransaction.with(PlantDescriptionController().apply { this.icon = icon })
//                .pushChangeHandler(FadeChangeHandler())
//                .popChangeHandler(FadeChangeHandler()))
    }

    private fun showNotifications() {
        router.pushController(RouterTransaction.with(NotificationsController())
                .pushChangeHandler(HorizontalChangeHandler())
                .popChangeHandler(HorizontalChangeHandler()))
    }
}