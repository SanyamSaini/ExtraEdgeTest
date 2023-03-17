package com.extra.edge.test.db

import androidx.room.TypeConverter
import com.extra.edge.test.model.Engines
import com.extra.edge.test.model.Measurement
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromString(value: String?): List<String> {
        val listType = object :
            TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun measurementToString(measurement: Measurement): String = Gson().toJson(measurement)

    @TypeConverter
    fun stringToMeasurement(string: String): Measurement =
        Gson().fromJson(string, Measurement::class.java)

    @TypeConverter
    fun enginesToString(engines: Engines): String = Gson().toJson(engines)

    @TypeConverter
    fun stringToEngines(string: String): Engines =
        Gson().fromJson(string, Engines::class.java)
}