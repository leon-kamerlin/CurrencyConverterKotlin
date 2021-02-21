package com.tenseconds.currencyconverter.ui.fragment.rates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tenseconds.currencyconverter.R
import com.tenseconds.currencyconverter.databinding.FragmentConverterBinding
import com.tenseconds.currencyconverter.databinding.FragmentRatesBinding
import com.tenseconds.currencyconverter.ui.activity.main.MainActivity
import com.tenseconds.currencyconverter.ui.fragment.converter.ConverterAdapter

class RatesFragment : Fragment() {
    companion object {
        fun newInstance(): RatesFragment {
            return RatesFragment()
        }
    }


    val activity: MainActivity by lazy { context as MainActivity }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentRatesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_rates, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = RatesAdapter(requireContext())
        binding.recyclerView.adapter = adapter

        activity.model.rates.observe(activity, {
            adapter.dataSet = it
        })


        return binding.root
    }
}