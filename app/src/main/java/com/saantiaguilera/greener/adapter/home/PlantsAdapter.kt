package com.saantiaguilera.greener.adapter.home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.random
import com.saantiaguilera.greener.screenSize
import com.saantiaguilera.greener.util.ResourcesUtil

/**
 * TODO Describe what this class do.
 */
typealias OnAddClickListener = () -> Unit
typealias OnItemClickListener = (Int) -> Unit
class PlantsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val count by lazy { (2..10).random() }

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
        val icon = ResourcesUtil.random()
        view.setOnClickListener { itemClickListener?.invoke(icon) }
        when (position) {
            in 0..(itemCount-2) -> {
                view.setImageResource(icon)
            }
            itemCount - 1 -> {
                view.setImageResource(R.drawable.ic_plus)
                view.setOnClickListener { addClickListener?.invoke() }
            }
        }
    }

    override fun getItemCount(): Int = count

}