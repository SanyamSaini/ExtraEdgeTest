package com.extra.edge.test.ui

import android.os.Bundle
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
            rocketImagesAdapter = RocketImagesAdapter(emptyList())
            binding.rvRocketImages.apply {
                layoutManager =
                    LinearLayoutManager(this@DetailActivity, RecyclerView.HORIZONTAL, false)
                hasFixedSize()
                adapter = rocketImagesAdapter
            }

            rocketId = intent.getStringExtra(Constants.ROCKET_ID)
            detailViewModel.getRocketDetail(rocketId!!)

            detailViewModel.rocketDetail.observe(this@DetailActivity) {
                supportActionBar?.title = it.name

                binding.rocket = it

                rocketImagesAdapter.submitList(it.flickerImages)
            }
        }

    }
}