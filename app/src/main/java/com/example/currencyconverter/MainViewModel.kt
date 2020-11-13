package com.example.currencyconverter

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.math.BigDecimal

class MainViewModel @ViewModelInject constructor() : ViewModel() {

    val pln = MutableLiveData<String>()
    val eur = MutableLiveData<String>()
    val usd = MutableLiveData<String>()

    init {
        pln.observeForever { onPlnChanged(it) }
    }

    private fun onPlnChanged(amount: String) {
        val pln = amount.toBigDecimalOrNull()
        eur.value = pln?.multiply(BigDecimal(EUR_PLN_RATE.toString()))?.toString()
        usd.value = pln?.multiply(BigDecimal(USD_PLN_RATE.toString()))?.toString()
    }

    companion object {

        private const val EUR_PLN_RATE = 0.22
        private const val USD_PLN_RATE = 0.26

    }

}