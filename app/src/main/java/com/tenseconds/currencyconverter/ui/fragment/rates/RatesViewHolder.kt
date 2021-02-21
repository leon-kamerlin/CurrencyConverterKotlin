package com.tenseconds.currencyconverter.ui.fragment.rates

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tenseconds.currencyconverter.currency.Currency
import com.tenseconds.currencyconverter.databinding.ItemCurrencyConverterBinding
import com.tenseconds.currencyconverter.databinding.ItemCurrencyRatesBinding

class RatesViewHolder(val binding: ItemCurrencyRatesBinding) : RecyclerView.ViewHolder(binding.root) {

    private fun rateFormat(number: Float): String {
        return String.format("%10.2f", number)
    }

    fun bindTo(currency: Currency, position: Int) {

    }
}