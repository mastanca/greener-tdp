package com.saantiaguilera.greener.adapter.search

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.random
import com.saantiaguilera.greener.screenSize
import com.saantiaguilera.greener.util.ResourcesUtil

/**
 * TODO Describe what this class do.
 */
class SearchAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var count = 0
    private var itemName: String? = null

    var clickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            object : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_search_list_item, null, false)) {}

    @SuppressLint("WrongViewCast")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<ImageView>(R.id.item_search_image).setImageResource(ResourcesUtil.random())
            findViewById<TextView>(R.id.item_search_name).apply {
                width = screenSize(context).first
                text = itemName
            }
            findViewById<TextView>(R.id.item_search_price).text = "$ ${(1..5000).random()}"
            setOnClickListener { clickListener?.onClick(it) }
        }
    }

    fun update(itemName: String) {
        this.count = (2..10).random()
        this.itemName = itemName
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = count

}