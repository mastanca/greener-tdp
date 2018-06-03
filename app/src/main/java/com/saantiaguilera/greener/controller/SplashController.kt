package com.saantiaguilera.greener.controller

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
 * TODO Describe what this class do.
 */
class SplashController : RxController() {

    private val delayTime = 5L

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        Observable.timer(delayTime, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe {
                    router.pushController(RouterTransaction.with(HomeController())
                            .pushChangeHandler(FadeChangeHandler())
                            .popChangeHandler(FadeChangeHandler()))
                }
        return inflater.inflate(R.layout.controller_splash, container, false)
    }

}