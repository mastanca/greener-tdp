package com.saantiaguilera.greener.controller

import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.bluelinelabs.conductor.rxlifecycle2.RxController
import com.saantiaguilera.greener.R

class MarketController : RxController() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (container.context as? AppCompatActivity)?.supportActionBar?.apply {
            title = resources!!.getString(R.string.app_name)
            show()
        }

        return inflater.inflate(R.layout.controller_market, container, false).apply {

            findViewById<TextView>(R.id.buy_product_tv).apply {
                setOnClickListener { showSearch() }
            }

            findViewById<BottomNavigationView>(R.id.bottom_navigation).apply {
                setOnNavigationItemSelectedListener { item -> showTab(item) }
            }
        }
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
        router.setRoot(RouterTransaction.with(HomeController()))
    }

    private fun showStoreTab() {
        // We are here
    }

    private fun showProfileTab() {
        Toast.makeText(applicationContext, "Profile tab", Toast.LENGTH_LONG).show()
    }

    private fun showSearch() {
        router.pushController(RouterTransaction.with(SearchController())
                .pushChangeHandler(HorizontalChangeHandler())
                .popChangeHandler(HorizontalChangeHandler()))
    }
}