package com.saantiaguilera.greener.controller

import android.app.ProgressDialog
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.bluelinelabs.conductor.rxlifecycle2.RxController
import com.saantiaguilera.greener.R
import kotlinx.android.extensions.CacheImplementation
import kotlinx.android.extensions.ContainerOptions
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.controller_registration.*

/**
 * Some class from the project
 */
@ContainerOptions(cache = CacheImplementation.NO_CACHE)
class RegistrationController : RxController(), LayoutContainer {

    override val containerView: View?
        get() = view

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (container.context as? AppCompatActivity)?.supportActionBar?.apply {
            title = "Registracion"
            show()
        }

        return inflater.inflate(R.layout.controller_registration, container, false).apply {
            findViewById<View>(R.id.btnSignup).setOnClickListener { signup() }
            findViewById<View>(R.id.linkLogin).setOnClickListener { login() }
        }
    }

    private fun signup() {
        if (!validate()) {
            onSignupFailed()
            return
        }

        btnSignup.isEnabled = false

        val progressDialog = ProgressDialog(containerView!!.context).apply {
            isIndeterminate = true
            setMessage("Creating Account...")
            show()
        }

        Handler().postDelayed({
            onSignupSuccess()
            progressDialog.dismiss()
        }, 3000)
    }


    private fun onSignupSuccess() {
        btnSignup.isEnabled = true
        login()
    }

    private fun onSignupFailed() {
        btnSignup.isEnabled = true
    }

    private fun login() {
        router.setRoot(RouterTransaction.with(LoginController())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

    private fun validate(): Boolean {
        var valid = true

        val name = inputName.text.toString()
        val address = inputAddress.text.toString()
        val email = inputEmail.text.toString()
        val mobile = inputMobile.text.toString()
        val password = inputPassword.text.toString()
        val reEnterPassword = inputReEnterPassword.text.toString()

        if (name.isEmpty() || name.length < 3) {
            inputName.error = "at least 3 characters"
            valid = false
        } else {
            inputName.error = null
        }

        if (address.isEmpty()) {
            inputAddress.error = "Enter Valid Address"
            valid = false
        } else {
            inputAddress.error = null
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            inputEmail.error = "enter a valid email address"
            valid = false
        } else {
            inputEmail.error = null
        }

        if (mobile.isEmpty() || mobile.length != 10) {
            inputMobile.error = "Enter Valid Mobile Number"
            valid = false
        } else {
            inputMobile.error = null
        }

        if (password.isEmpty() || password.length < 4 || password.length > 10) {
            inputPassword.error = "between 4 and 10 alphanumeric characters"
            valid = false
        } else {
            inputPassword.error = null
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length < 4 || reEnterPassword.length > 10 || reEnterPassword != password) {
            inputReEnterPassword.error = "Password Do not match"
            valid = false
        } else {
            inputReEnterPassword.error = null
        }

        return valid
    }

}