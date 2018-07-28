package com.saantiaguilera.greener.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.entities.plant.Plant
import com.saantiaguilera.greener.util.ResourcesUtil

class PlantItemView(ctx: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(ctx, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context)
                .inflate(R.layout.view_plants_menu_list_item, this, true)
        orientation = VERTICAL
    }

    fun setViewWith(plant: Plant) {
        val imgView = findViewById<ImageView>(R.id.view_plants_menu_item_pic)
        val textView = findViewById<TextView>(R.id.view_plants_menu_item_text)
        imgView.setImageResource(plant.getIcon(context))
        textView.setText(plant.name)
    }

}