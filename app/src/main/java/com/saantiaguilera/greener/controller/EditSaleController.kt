package com.saantiaguilera.greener.controller

import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bluelinelabs.conductor.rxlifecycle2.RxController
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.entities.database.AppDB
import com.saantiaguilera.greener.model.Sale

class EditSaleController : RxController() {

    lateinit var sale: Sale
    var position: Int = 0
    lateinit var priceTv: TextView
    lateinit var quantityTv: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (container.context as? AppCompatActivity)?.supportActionBar?.apply {
            title = "Edit sale's details"
            show()
        }

        return inflater.inflate(R.layout.controller_sale_detail, container, false).apply {

            priceTv = findViewById(R.id.sale_price_tv)
            quantityTv = findViewById(R.id.sale_quantity_tv)

            priceTv.text = "$${sale.price}"
            quantityTv.text = "${sale.quantity}"

            findViewById<TextView>(R.id.product_name).text = sale.name
            findViewById<ImageView>(R.id.product_icon).setImageResource(sale.getIcon(context))

            findViewById<FloatingActionButton>(R.id.price_subtract_button).apply {
                setOnClickListener { subtractPrice() }
            }

            findViewById<FloatingActionButton>(R.id.price_add_button).apply {
                setOnClickListener { addPrice() }
            }

            findViewById<FloatingActionButton>(R.id.quantity_subtract_button).apply {
                setOnClickListener { subtractQuantity() }
            }

            findViewById<FloatingActionButton>(R.id.quantity_add_button).apply {
                setOnClickListener { addQuantity() }
            }

            findViewById<FloatingActionButton>(R.id.confirm_button).apply {
                setOnClickListener { confirmSale() }
            }

        }
    }

    private fun subtractPrice() {
        var price = priceTv.text.substring(1).toInt()
        price = if (price == 0) 0 else (price - 1)
        priceTv.text = "$$price"
    }

    private fun addPrice() {
        val price = priceTv.text.substring(1).toInt()
        priceTv.text = "$${price + 1}"
    }

    private fun subtractQuantity() {
        var quantity = quantityTv.text.toString().toInt()
        quantity = if (quantity == 0) 0 else (quantity - 1)
        quantityTv.text = "$quantity"
    }

    private fun addQuantity() {
        val quantity = quantityTv.text.toString().toInt()
        quantityTv.text = "${quantity + 1}"
    }

    private fun confirmSale() {
        val price = priceTv.text.substring(1).toInt()
        val quantity = quantityTv.text.toString().toInt()

        if (price == 0 || quantity == 0)
            Toast.makeText(applicationContext, "Values can't be zero", Toast.LENGTH_SHORT).show()
        else
            createSale(price, quantity)
    }

    private fun createSale(price: Int, quantity: Int) {
        val sale = Sale(sale.name, "", quantity, price, sale.icon)
        AppDB.replaceSale(position, sale, applicationContext)

        router.popCurrentController()
    }

}