package com.saantiaguilera.greener.adapter.market

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.model.Sale
import kotlinx.android.synthetic.main.sale_list_item.view.*

class SalesAdapter : RecyclerView.Adapter<SaleViewHolder>() {

    private val sales = listOf(
            Sale("Palta", "Descripcion", 5, 30, "palta"),
            Sale("Lechuga", "Descripcion", 15, 20, "lechuga"),
            Sale("Zanahoria", "Descripcion", 10, 15, "zanahoria"),
            Sale("Albahaca", "Descripcion", 5, 20, "albahaca")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleViewHolder {
        return SaleViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.sale_list_item,
                                null, false))
    }

    override fun getItemCount(): Int = sales.size

    override fun onBindViewHolder(holder: SaleViewHolder, position: Int) {
        holder.icon?.setImageResource(sales[position].getIcon(holder.itemView.context))
        holder.name?.text = sales[position].name
        holder.quantity?.text = "Cantidad: ${sales[position].quantity}"
        holder.price?.text = "Precio: $${sales[position].price}"
    }

}

class SaleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name = view.sale_name
    val quantity = view.sale_quantity
    val price = view.sale_price
    val icon = view.sale_image
}