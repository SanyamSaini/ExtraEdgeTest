package com.extra.edge.test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.extra.edge.test.R
import com.google.android.material.imageview.ShapeableImageView

class RocketImagesAdapter(private var images: List<String>) :
    RecyclerView.Adapter<RocketImagesAdapter.RocketImageViewHolder>() {

    class RocketImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ivRocket: ShapeableImageView = view.findViewById(R.id.ivRocket)

        fun bind(item: String, context: Context) {

            if (item.isNotEmpty())
                Glide.with(context)
                    .load(item)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image)
                    .into(ivRocket)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketImageViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rocket_image, parent, false)
        return RocketImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: RocketImageViewHolder, position: Int) {
        val item = images[position]
        val context = holder.itemView.context
        holder.bind(item, context)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun submitList(updateList: List<String>) {
        images = updateList
        notifyDataSetChanged()
    }
}