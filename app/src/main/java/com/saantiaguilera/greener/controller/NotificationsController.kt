package com.saantiaguilera.greener.controller

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.rxlifecycle2.RxController
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.adapter.home.NotificationsAdapter

class NotificationsController : RxController() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (container.context as? AppCompatActivity)?.supportActionBar?.apply {
            title = "Notificaciones"
            show()
        }

        return inflater.inflate(R.layout.controller_notifications, container, false).apply {
            findViewById<RecyclerView>(R.id.notifications_recycler).apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = NotificationsAdapter()
            }
        }
    }

}