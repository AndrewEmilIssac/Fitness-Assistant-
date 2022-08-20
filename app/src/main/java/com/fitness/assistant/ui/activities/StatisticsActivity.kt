

package com.fitness.assistant.ui.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.fitness.assistant.R
import com.fitness.assistant.databinding.ActivityStatisticsBinding
import com.fitness.assistant.ui.adapters.StatisticsPagerAdapter
import com.fitness.assistant.util.toast
import com.fitness.assistant.viewModels.StatisticsViewModel
import javax.inject.Inject

class StatisticsActivity : ClassicActivity() {

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, modelFactory).get(StatisticsViewModel::class.java)
    }
    private lateinit var binding: ActivityStatisticsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBar()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_statistics)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        setUpView()
        setObservers()
    }

    private fun setObservers() {
        viewModel.deleteProduct.observe(this, Observer { event ->
            event.getContentIfNotHandled()
                ?.let {
                    toast(getString(R.string.error_firebase_save))
                }
        })
    }

    private fun setUpView() {
        setUpAppBar()
        setUpTabLayout()
    }


    private fun setUpTabLayout() {
        val viewPager = findViewById<ViewPager>(R.id.pager)
        viewPager.adapter = StatisticsPagerAdapter(
            supportFragmentManager,
            arrayOf(getString(R.string.month), getString(R.string.day))
        )
        binding.tablayout.setupWithViewPager(viewPager)
    }


    private fun setUpAppBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        // update day in the HomeActivity if data has been changed
        if (viewModel.dataChanged.value == true) setResult(HomeActivity.RESULT_CODE_UPDATE_DAY)
        super.onBackPressed()
    }


}
