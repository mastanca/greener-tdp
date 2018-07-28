package com.saantiaguilera.greener.adapter.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.entities.database.AppDB
import com.saantiaguilera.greener.entities.plant.Plant
import com.saantiaguilera.greener.random
import com.saantiaguilera.greener.screenSize
import com.saantiaguilera.greener.util.ResourcesUtil
import kotlin.coroutines.experimental.coroutineContext

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
        return object : RecyclerView.ViewHolder(ImageView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(screenSize(context).first / 3, 280)
            scaleType = ImageView.ScaleType.CENTER_INSIDE
        }){}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder.itemView as ImageView
        view.setPadding(0, 16, 0, 16)
        when (position) {
            in 0..(itemCount-2) -> {
                val plant = plants[position]
                view.setOnClickListener { itemClickListener?.invoke(plant) }
                view.setImageResource(plant.getIcon(holder.itemView.context))
            }
            itemCount - 1 -> {
                view.setImageResource(R.drawable.ic_plus)
                view.setOnClickListener { addClickListener?.invoke() }
            }
        }
    }

    override fun getItemCount(): Int = plants.size + 1

}