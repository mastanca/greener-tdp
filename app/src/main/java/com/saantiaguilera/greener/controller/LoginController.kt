package com.saantiaguilera.greener.controller

import com.bluelinelabs.conductor.rxlifecycle2.RxController
import kotlinx.android.extensions.LayoutContainer
import android.app.ProgressDialog
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.saantiaguilera.greener.R

import kotlinx.android.synthetic.main.controller_login.*
import kotlinx.android.extensions.CacheImplementation
import kotlinx.android.extensions.ContainerOptions

/**
 * Some class from the project
 */
@ContainerOptions(cache = CacheImplementation.NO_CACHE)
class LoginController : RxController(), LayoutContainer {

    override val containerView: View?
        get() = view

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (container.context as? AppCompatActivity)?.supportActionBar?.apply {
            title = "Login"
            show()
        }

        return inflater.inflate(R.layout.controller_login, container, false).apply {
            findViewById<View>(R.id.btnLogin).setOnClickListener { login() }
            findViewById<View>(R.id.linkSignup).setOnClickListener {
                signup()
            }
        }
    }

    private fun login() {
        if (!validate()) {
            onLoginFailed()
            return
        }

        btnLogin.isEnabled = false

        val progressDialog = ProgressDialog(containerView!!.context, R.style.AppAlertDialog).apply {
            isIndeterminate = true
            setMessage("Authenticating...")
            show()
        }

        Handler().postDelayed({
            onLoginSuccess()
            progressDialog.dismiss()
        }, 3000)
    }

    private fun onLoginSuccess() {
        btnLogin.isEnabled = true
        home()
    }

    private fun onLoginFailed() {
        btnLogin.isEnabled = true
    }

    private fun home() {
        router.setRoot(RouterTransaction.with(HomeController())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

    private fun signup() {
        router.setRoot(RouterTransaction.with(RegistrationController())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

    private fun validate(): Boolean {
        var valid = true

        val email = inputEmail.text.toString()
        val password = inputPassword.text.toString()

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            inputEmail.error = "enter a valid email address"
            valid = false
        } else {
            inputEmail.error = null
        }

        if (password.isEmpty() || password.length < 4 || password.length > 10) {
            inputPassword.error = "between 4 and 10 alphanumeric characters"
            valid = false
        } else {
            inputPassword.error = null
        }

        return valid
    }

}