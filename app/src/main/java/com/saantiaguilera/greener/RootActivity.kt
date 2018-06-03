package com.saantiaguilera.greener

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.saantiaguilera.greener.controller.SplashController
import android.R.attr.y
import android.R.attr.x
import android.content.Context
import android.graphics.Point
import android.view.Display
import android.view.WindowManager
import java.util.*


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

fun Any?.screenSize(context: Context): Pair<Int, Int> {
    val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = wm.defaultDisplay
    val size = Point()
    display.getSize(size)
    return Pair(size.x, size.y)
}

fun ClosedRange<Int>.random() =
        Random().nextInt(endInclusive - start) +  start