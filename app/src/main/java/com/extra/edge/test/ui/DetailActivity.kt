package com.extra.edge.test.ui

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.extra.edge.test.R
import com.extra.edge.test.adapter.RocketImagesAdapter
import com.extra.edge.test.databinding.ActivityDetailBinding
import com.extra.edge.test.utils.Constants
import com.extra.edge.test.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailViewModel
    private var rocketId: String? = ""
    private lateinit var binding: ActivityDetailBinding
    private lateinit var rocketImagesAdapter: RocketImagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        if (intent == null)
            finish()
        else {
            rocketId = intent.getStringExtra(Constants.ROCKET_ID)
            rocketImagesAdapter = RocketImagesAdapter(emptyList())
            initView()
            getData()
        }
    }

    private fun initView() {
        binding.rvRocketImages.apply {
            layoutManager =
                LinearLayoutManager(this@DetailActivity, RecyclerView.HORIZONTAL, false)
            hasFixedSize()
            adapter = rocketImagesAdapter
        }
    }

    private fun getData() {
        detailViewModel.getRocketDetail(rocketId!!)
        detailViewModel.rocketDetail.observe(this@DetailActivity) {

            if (it != null) {
                supportActionBar?.title = it.name
                binding.rocket = it
                rocketImagesAdapter.submitList(it.flickerImages)
                binding.groupDetail.visibility = View.VISIBLE
            } else {
                Handler(mainLooper).post {
                    Toast.makeText(this, getString(R.string.error_msg), Toast.LENGTH_LONG).show()
                }
            }
            binding.pbDetail.visibility = View.GONE
        }
    }
}