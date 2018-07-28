package com.saantiaguilera.greener.controller

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.rxlifecycle2.RxController
import com.saantiaguilera.greener.R
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler

/**
 * Some class from the project
 */
class SplashController : RxController() {

    private val delayTime = 5L
    private val requestAllPermissions = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (container.context as? AppCompatActivity)?.supportActionBar?.hide()

        requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), requestAllPermissions)

        Observable.timer(delayTime, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe {
                    router.setRoot(RouterTransaction.with(RegistrationController())
                            .pushChangeHandler(FadeChangeHandler())
                            .popChangeHandler(FadeChangeHandler()))
                }
        return inflater.inflate(R.layout.controller_splash, container, false)
    }

}