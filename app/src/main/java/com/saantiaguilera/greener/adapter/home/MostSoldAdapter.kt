package com.saantiaguilera.greener.adapter.home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.entities.plant.Plant
import com.saantiaguilera.greener.random
import com.saantiaguilera.greener.util.ResourcesUtil

/**
 * Some class from the project
 */
class MostSoldAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var plants: Array<Plant>
    var clickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(ImageView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(240, 240)
        }){}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder.itemView as ImageView
        val icon = ResourcesUtil.random()
        view.setOnClickListener { clickListener?.invoke(plants[position]) }
        view.setImageResource(icon)
    }

    override fun getItemCount(): Int = plants.size

}