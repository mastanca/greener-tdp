package com.saantiaguilera.greener.adapter.description

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.saantiaguilera.greener.model.History
import com.saantiaguilera.greener.view.HistoryItemView

class HistoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var history: Array<History>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(HistoryItemView(parent.context)) {}
    }

    override fun getItemCount(): Int = history.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var view = holder.itemView as HistoryItemView
        view.setViewWith(history[position])
        if (position % 2 == 0) view.highlightBackground()
    }

}