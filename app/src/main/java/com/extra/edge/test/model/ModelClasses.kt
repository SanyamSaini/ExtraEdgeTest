package com.extra.edge.test.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "rocket")

data class Rocket(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("stages")
    val stages: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("wikipedia")
    val wikipedia: String,
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("cost_per_launch")
    val cost_per_launch: Long,
    @SerializedName("success_rate_pct")
    val success_rate_pct: Double,
    @SerializedName("height")
    val height: Measurement,
    @SerializedName("diameter")
    val diameter: Measurement,
    @SerializedName("flickr_images")
    val flickerImages: List<String>,
    @SerializedName("engines")
    val engines: Engines
)

@Entity(tableName = "measurement")
data class Measurement(
    @PrimaryKey(autoGenerate = true)
    val _id: Int,
    @SerializedName("meters")
    val meters: Double,
    @SerializedName("feet")
    val feet: Double
)

@Entity(tableName = "engines")
data class Engines(
    @PrimaryKey(autoGenerate = true)
    val _id: Int,
    @SerializedName("number")
    val number: Int
)