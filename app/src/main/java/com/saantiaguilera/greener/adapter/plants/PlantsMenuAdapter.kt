package com.saantiaguilera.greener.adapter.plants

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.saantiaguilera.greener.entities.database.AppDB
import com.saantiaguilera.greener.entities.plant.Plant
import com.saantiaguilera.greener.view.PlantItemView

typealias OnItemClickListener = (Plant) -> Unit
class PlantsMenuAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var plants: Array<Plant> = arrayOf()
    var clickListener: OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(PlantItemView(parent.context)) {}
    }


    override fun getItemCount(): Int = plants.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder.itemView as PlantItemView).setViewWith(plants[position])
        holder.itemView.apply {
            setOnClickListener { clickListener?.invoke(plants[position]) }
        }
    }
}