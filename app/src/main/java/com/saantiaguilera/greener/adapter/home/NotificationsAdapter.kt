package com.saantiaguilera.greener.adapter.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.random

class NotificationsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val count by lazy { (2..10).random() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            object : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.notification_list_item, null, false)) {}


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int = count

}