package com.saantiaguilera.greener.adapter.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.model.Notification
import kotlinx.android.synthetic.main.notification_list_item.view.*


class NotificationsAdapter : RecyclerView.Adapter<NotificationViewHolder>() {

    private val notifications = listOf(
            Notification("Palta 1", "Necesita más agua", "20/05/2018 16:00"),
            Notification("Lechuga", "Necesita más luz", "20/05/2018 12:00"),
            Notification("¡ATENCIÓN!", "Una tormenta se avecina, resguarda tus cultivos", "19/05/2018 18:00"),
            Notification("Tomate", "Necesita más luz", "19/05/2018 17:00"),
            Notification("Lechuga", "Está en estado crítico", "18/05/2018 16:00")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.notification_list_item,
                                null, false))
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.title?.text = notifications[position].title
        holder.body?.text = notifications[position].body
        holder.time?.text = notifications[position].time
    }

    override fun getItemCount(): Int = notifications.size
}

class NotificationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title = view.notification_title
    val body = view.notification_body
    val time = view.notification_time
}