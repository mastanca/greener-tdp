package com.saantiaguilera.greener

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.saantiaguilera.greener.controller.SplashController

class RootActivity : AppCompatActivity() {

    private var router: Router? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_root)

        router = Conductor.attachRouter(this, findViewById(R.id.activity_root_view), savedInstanceState)
        if (!router!!.hasRootController()) {
            router!!.setRoot(RouterTransaction.with(SplashController()))
        }
    }

    override fun onBackPressed() {
        if (!router!!.handleBack()) {
            super.onBackPressed()
        }
    }

}
