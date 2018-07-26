package com.saantiaguilera.greener.adapter.home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.random
import com.saantiaguilera.greener.util.ResourcesUtil

/**
 * TODO Describe what this class do.
 */
class MostSoldAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val count by lazy { (2..10).random() }

    var clickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(ImageView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(240, 240)
        }){}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder.itemView as ImageView
        val icon = ResourcesUtil.random()
        view.setOnClickListener { /*clickListener?.invoke(icon)*/ }
        view.setImageResource(icon)
    }

    override fun getItemCount(): Int = count

}