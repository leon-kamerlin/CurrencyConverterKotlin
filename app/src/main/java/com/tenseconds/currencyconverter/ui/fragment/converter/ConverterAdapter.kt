package com.tenseconds.currencyconverter.ui.fragment.converter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tenseconds.currencyconverter.R
import com.tenseconds.currencyconverter.currency.Currency
import com.tenseconds.currencyconverter.databinding.ItemCurrencyConverterBinding
import com.tenseconds.currencyconverter.ui.fragment.CurrencyDiffCallback


class ConverterAdapter(private val context: Context) :
    RecyclerView.Adapter<ConverterViewHolder>() {
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

    var baseValue: Float = 1f
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ConverterViewHolder {
        val binding = DataBindingUtil.inflate<ItemCurrencyConverterBinding>(
            LayoutInflater.from(
                viewGroup.context
            ), R.layout.item_currency_converter, viewGroup, false
        )
        return ConverterViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ConverterViewHolder, position: Int) {
        val item = dataSet.toList()[position]
        viewHolder.bindTo(item, position, baseValue)
    }

    private fun updateList(oldList: List<Currency>, newList: List<Currency>) {
        val diffResult = DiffUtil.calculateDiff(CurrencyDiffCallback(oldList, newList))
        diffResult.dispatchUpdatesTo(this)
    }




    override fun getItemCount() = dataSet.size
}