package com.saantiaguilera.greener.controller

import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.bluelinelabs.conductor.rxlifecycle2.RxController
import com.saantiaguilera.greener.R
import com.saantiaguilera.greener.adapter.search.SearchAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

/**
 * TODO Describe what this class do.
 */
class SearchController : RxController() {

    val debouncer: PublishSubject<String> by lazy { PublishSubject.create<String>() }

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (container.context as? AppCompatActivity)?.supportActionBar?.hide()

        debouncer.compose(bindToLifecycle())
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { update(it) }

        return inflater.inflate(R.layout.controller_search, container, false).apply {
            findViewById<EditText>(R.id.controller_search_field).addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun afterTextChanged(s: Editable?) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    debouncer.onNext(s!!.toString())
                }
            })

            recyclerView = findViewById<RecyclerView>(R.id.controller_search_recycler_field).apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = SearchAdapter().apply { clickListener = View.OnClickListener { showShopForProduct() } }
            }

            findViewById<BottomNavigationView>(R.id.bottom_navigation).apply {
                setOnNavigationItemSelectedListener { item -> showTab(item) }
            }
        }
    }

    private fun showTab(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_home -> showHomeTab()
            R.id.action_store -> showStoreTab()
            R.id.action_profile -> showProfileTab()
        }
        return true
    }

    private fun showHomeTab() {
        router.setRoot(RouterTransaction.with(HomeController()))
    }

    private fun showStoreTab() {
        // We are here
    }

    private fun showProfileTab() {
        Toast.makeText(applicationContext, "Profile tab", Toast.LENGTH_LONG).show()
    }

    private fun update(itemName: String) {
        (recyclerView.adapter as SearchAdapter).update(itemName)
    }

    private fun showShopForProduct() {
        router.pushController(RouterTransaction.with(SingleProductShopController())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

}