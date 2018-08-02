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
            title = "Registro"
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
            setMessage("Creando cuenta...")
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
            inputName.error = "al menos 3 caracteres"
            valid = false
        } else {
            inputName.error = null
        }

        if (address.isEmpty()) {
            inputAddress.error = "Ingresa una direccion correcta"
            valid = false
        } else {
            inputAddress.error = null
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            inputEmail.error = "Ingresa un email correcto"
            valid = false
        } else {
            inputEmail.error = null
        }

        if (mobile.isEmpty() || mobile.length != 10) {
            inputMobile.error = "Ingresa un numero correcto"
            valid = false
        } else {
            inputMobile.error = null
        }

        if (password.isEmpty() || password.length < 4 || password.length > 10) {
            inputPassword.error = "entre 4 y 10 caracteres alfanumericos"
            valid = false
        } else {
            inputPassword.error = null
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length < 4 || reEnterPassword.length > 10 || reEnterPassword != password) {
            inputReEnterPassword.error = "La contraseña no coincide"
            valid = false
        } else {
            inputReEnterPassword.error = null
        }

        return valid
    }

}