package com.extra.edge.test.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.extra.edge.test.R
import com.extra.edge.test.adapter.RocketAdapter
import com.extra.edge.test.databinding.ActivityMainBinding
import com.extra.edge.test.model.Rocket
import com.extra.edge.test.utils.Constants
import com.extra.edge.test.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RocketAdapter.RocketClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rocketAdapter: RocketAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        rocketAdapter = RocketAdapter(this)

        initRecyView()
    }

    private fun initRecyView() {
        binding.rvRockets.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            hasFixedSize()
            adapter = rocketAdapter
        }

        mainViewModel.rockets.observe(this) {
            rocketAdapter.submitList(it)
        }
    }

    override fun rocketClick(item: Rocket) {
        startActivity(
            Intent(this, DetailActivity::class.java)
                .putExtra(Constants.ROCKET_ID, item.id)
        )
    }
}