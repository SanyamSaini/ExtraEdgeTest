package com.extra.edge.test.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter


@BindingAdapter("measurement", "feet", "meters")
fun setFormattedText(view: TextView, measurement: String, feet: Double, meters: Double) {
    val inches = meters * 39.37
    val inchesString = String.format("%.2f", inches)
    if (measurement == "height") {
        view.text = "Height feet/inches : $feet/$inchesString"
    } else {
        view.text = "Diameter feet/inches : $feet/$inchesString"
    }
}