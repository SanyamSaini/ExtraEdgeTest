package com.extra.edge.test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.extra.edge.test.R
import com.extra.edge.test.model.Rocket
import com.google.android.material.imageview.ShapeableImageView

class RocketAdapter(private val rocketClickListener: RocketClickListener) :
    ListAdapter<Rocket, RocketAdapter.RocketViewHolder>(DiffUtil()) {

    interface RocketClickListener {
        fun rocketClick(item: Rocket)
    }

    class RocketViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ivRocket: ShapeableImageView = view.findViewById(R.id.ivRocket)
        private val tvName: TextView = view.findViewById(R.id.tvName)
        private val tvCountry: TextView = view.findViewById(R.id.tvCountry)
        private val tvEngineCount: TextView = view.findViewById(R.id.tvEngineCount)
        private val cvRocketItem: ConstraintLayout = view.findViewById(R.id.cvRocketItem)

        fun bind(item: Rocket, context: Context, rocketClickListener: RocketClickListener) {
            tvName.text = context.getString(R.string.name_, item.name)
            tvCountry.text = context.getString(R.string.country_, item.country)
            tvEngineCount.text =
                context.getString(R.string.no_of_engines_, item.stages.toString())

            if (item.flickerImages != null)
                Glide.with(context).load(item.flickerImages[0]).into(ivRocket)

            cvRocketItem.setOnClickListener {
                rocketClickListener.rocketClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_main_page, parent, false)
        return RocketViewHolder(view)
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        val item = getItem(position)
        val context = holder.itemView.context
        holder.bind(item, context, rocketClickListener)

    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Rocket>() {
        override fun areItemsTheSame(oldItem: Rocket, newItem: Rocket): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Rocket, newItem: Rocket): Boolean {
            return oldItem == newItem
        }
    }
}