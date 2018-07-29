package com.saantiaguilera.greener.adapter.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.entities.plant.Plant

/**
 * Some class from the project
 */
class ProductsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var plants: Array<Plant>
    var clickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.product_item,
                                null, false)
        ){}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder.itemView as LinearLayout
        val resource = plants[position].getIcon(holder.itemView.context)
        view.setOnClickListener { clickListener?.invoke(plants[position]) }
        view.findViewById<ImageView>(R.id.product_image).setImageResource(resource)
        view.findViewById<TextView>(R.id.product_name_tv).text = plants[position].name
    }

    override fun getItemCount(): Int = plants.size

}