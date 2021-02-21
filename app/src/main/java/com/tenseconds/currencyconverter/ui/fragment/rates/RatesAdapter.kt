package com.tenseconds.currencyconverter.ui.fragment.rates

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tenseconds.currencyconverter.R
import com.tenseconds.currencyconverter.currency.Currency
import com.tenseconds.currencyconverter.databinding.ItemCurrencyConverterBinding
import com.tenseconds.currencyconverter.databinding.ItemCurrencyRatesBinding
import com.tenseconds.currencyconverter.ui.fragment.CurrencyDiffCallback

class RatesAdapter(private val context: Context) : RecyclerView.Adapter<RatesViewHolder>() {
    var dataSet: Set<Currency> = setOf()
        set(value) {
            if (dataSet.isEmpty()) {
                field = value
                notifyDataSetChanged()
            } else {
                updateList(dataSet.toList(), value.toList())
                field = value;
            }

        }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RatesViewHolder {

        val binding = DataBindingUtil.inflate<ItemCurrencyRatesBinding>(
            LayoutInflater.from(
                viewGroup.context
            ), R.layout.item_currency_rates, viewGroup, false
        )

        return RatesViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: RatesViewHolder, position: Int) {
        val item = dataSet.toList()[position]
        viewHolder.bindTo(item, position)

    }

    override fun getItemCount() = dataSet.size

    private fun updateList(oldList: List<Currency>, newList: List<Currency>) {
        val diffResult = DiffUtil.calculateDiff(CurrencyDiffCallback(oldList, newList))
        diffResult.dispatchUpdatesTo(this)
    }
}