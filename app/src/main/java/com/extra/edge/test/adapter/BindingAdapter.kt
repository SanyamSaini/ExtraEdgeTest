package com.extra.edge.test.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("feet", "meters", "measureType")
fun setFormattedText(view: TextView, feet: Double, meters: Double, measureType: String) {
    val inches = meters * 39.37
    if (measureType == "height") {
        view.text = "Height feet/inches : $feet/$inches"
    } else {
        view.text = "Diameter feet/inches : $feet/$inches"
    }
}