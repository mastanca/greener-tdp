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
typealias OnAddClickListener = () -> Unit

typealias OnItemClickListener = (Plant) -> Unit

class PlantsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var plants: Array<Plant>

    var addClickListener: OnAddClickListener? = null
    var itemClickListener: OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.plant_item,
                                null, false)
        ) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder.itemView as LinearLayout
        view.setPadding(0, 16, 0, 16)
        when (position) {
            in 0..(itemCount - 2) -> {
                val plant = plants[position]
                view.setOnClickListener { itemClickListener?.invoke(plant) }
                view.findViewById<ImageView>(R.id.plant_image)
                        .setImageResource(plant.getIcon(holder.itemView.context))
                view.findViewById<TextView>(R.id.plant_name_tv).text = plant.name
            }
            itemCount - 1 -> {
                view.findViewById<ImageView>(R.id.plant_image).apply {
                    setImageResource(R.drawable.ic_plus)
                    scaleType = ImageView.ScaleType.CENTER_INSIDE
                }
                view.setOnClickListener { addClickListener?.invoke() }
            }
        }
    }

    override fun getItemCount(): Int = plants.size + 1

}