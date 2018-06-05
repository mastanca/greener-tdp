package com.saantiaguilera.greener.adapter.home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.random
import com.saantiaguilera.greener.screenSize

/**
 * TODO Describe what this class do.
 */
typealias OnAddClickListener = () -> Unit
class PlantsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val count by lazy { (2..10).random() }

    var addClickListener: OnAddClickListener? = null
    var itemClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(ImageView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(screenSize(context).first / 3, 280)
            scaleType = ImageView.ScaleType.CENTER_INSIDE
        }){}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder.itemView as ImageView
        view.setOnClickListener { itemClickListener?.onClick(it) }
        when (position) {
            in 0..(itemCount-2) -> view.setImageResource(R.mipmap.ic_launcher_round)
            itemCount - 1 -> {
                view.setImageResource(R.mipmap.ic_launcher_round)
                view.setOnClickListener { addClickListener?.invoke() }
            }
        }
    }

    override fun getItemCount(): Int = count

}