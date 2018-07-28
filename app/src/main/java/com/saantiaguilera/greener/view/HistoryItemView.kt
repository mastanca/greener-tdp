package com.saantiaguilera.greener.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.model.History
import kotlinx.android.synthetic.main.view_plants_description_history_item.view.*
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView



class HistoryItemView(ctx: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(ctx, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context)
                .inflate(R.layout.view_plants_description_history_item, this, true)
        orientation = VERTICAL
        layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun setViewWith(history: History) {
        dateText.setText(history.getDate())
        actionText.setText(history.getAction())
    }

    fun highlightBackground() {
        setBackgroundColor(resources.getColor(R.color.colorPrimaryLighter))
    }
}