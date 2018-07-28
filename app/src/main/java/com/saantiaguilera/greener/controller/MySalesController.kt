package com.saantiaguilera.greener.controller

import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.rxlifecycle2.RxController
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.adapter.market.SalesAdapter

class MySalesController : RxController() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (container.context as? AppCompatActivity)?.supportActionBar?.apply {
            title = "Mis productos en venta"
            show()
        }

        return inflater.inflate(R.layout.controller_my_sales, container, false).apply {
            findViewById<RecyclerView>(R.id.sales_recycler).apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = SalesAdapter()
            }

            findViewById<FloatingActionButton>(R.id.add_button).apply {
                setOnClickListener { addProduct() }
            }
        }
    }

    private fun addProduct() {
    }
}