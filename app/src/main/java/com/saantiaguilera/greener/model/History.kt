package com.saantiaguilera.greener.model

class History(private var date: String, private var action: String) {

    fun getDate(): String {
        return date
    }

    fun getAction(): String {
        return action
    }

}