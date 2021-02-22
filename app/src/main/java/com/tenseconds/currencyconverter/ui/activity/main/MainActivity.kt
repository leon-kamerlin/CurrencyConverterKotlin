package com.tenseconds.currencyconverter.ui.activity.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tenseconds.currencyconverter.R
import com.tenseconds.currencyconverter.currency.CurrencyApi
import com.tenseconds.currencyconverter.databinding.ActivityMainBinding
import com.tenseconds.currencyconverter.ui.fragment.converter.ConverterFragment
import com.tenseconds.currencyconverter.ui.fragment.rates.RatesFragment
import io.reactivex.Observable
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val repository: CurrencyApi.Repository by inject()
    private val connectivity: Observable<Connectivity> by inject()
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    val model: MainViewModel by viewModels {
        MainViewModelFactory(repository, connectivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityMainBinding.inflate(layoutInflater)

        toolbarSetup()
        setupViewPager(binding.viewPagerBottom, binding.tabs)


        model.dataSource.observe(this, {
            println(it.rates.getCurrencies())
        })
    }

    private fun toolbarSetup() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowCustomEnabled(true) // enable overriding the default toolbar layout
        supportActionBar?.setDisplayShowTitleEnabled(false) // disable the default ti
    }


    private fun setupViewPager(viewPager: ViewPager2, tabLayout: TabLayout) {
        val adapter = ViewPagerAdapterHelper(
            supportFragmentManager,
            lifecycle
        )
        adapter.fragmentList.add(RatesFragment.newInstance())
        adapter.fragmentList.add(ConverterFragment.newInstance())
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPager.adapter = adapter

        val tabLayoutMediator = TabLayoutMediator(
            tabLayout, viewPager, true
        ) { tab: TabLayout.Tab, position: Int ->
            when (position) {
                0 -> tab.setText(R.string.all_rates)
                1 -> tab.setText(R.string.converter)
            }
        }
        tabLayoutMediator.attach()
    }
}