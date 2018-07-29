package com.saantiaguilera.greener.controller

import android.app.AlertDialog
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler
import com.bluelinelabs.conductor.rxlifecycle2.RxController
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.adapter.market.SalesAdapter
import com.saantiaguilera.greener.entities.database.AppDB
import com.saantiaguilera.greener.util.SwipeToDeleteCallback

class MySalesController : RxController() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (container.context as? AppCompatActivity)?.supportActionBar?.apply {
            title = "My sales"
            show()
        }

        return inflater.inflate(R.layout.controller_my_sales, container, false).apply {
            val recyclerView = findViewById<RecyclerView>(R.id.sales_recycler).apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = SalesAdapter().apply {
                    sales = AppDB.getSales(context)
                }
            }

            setSwipeToRecycler(recyclerView)

            findViewById<FloatingActionButton>(R.id.add_button).apply {
                setOnClickListener { addSale() }
            }
        }
    }

    private fun View.setSwipeToRecycler(recyclerView: RecyclerView) {
        val swipeHandler = object : SwipeToDeleteCallback(context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val builder = AlertDialog.Builder(activity)
                builder.setMessage("¿Está seguro que quiere eliminar la venta?")
                        .setTitle("ATENCIÓN")

                builder.setPositiveButton("Si") { dialog, _ ->
                    dialog.dismiss()
                    val adapter = recyclerView.adapter as SalesAdapter
                    AppDB.removeSale(viewHolder.adapterPosition, context)
                    adapter.apply {
                        sales = AppDB.getSales(context)
                        notifyItemRemoved(viewHolder.adapterPosition)
                    }
                }
                builder.setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                    recyclerView.adapter.notifyDataSetChanged()
                }

                val dialog = builder.create()
                dialog.show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun addSale() {
        router.pushController(RouterTransaction.with(SaleController())
                .pushChangeHandler(VerticalChangeHandler())
                .popChangeHandler(VerticalChangeHandler()))
    }
}