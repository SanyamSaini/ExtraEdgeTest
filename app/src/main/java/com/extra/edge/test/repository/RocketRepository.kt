package com.extra.edge.test.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.extra.edge.test.api.RocketApi
import com.extra.edge.test.db.RocketDatabase
import com.extra.edge.test.model.Rocket
import com.extra.edge.test.utils.NetworkUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RocketRepository @Inject constructor(@ApplicationContext val context: Context) {

    @Inject
    lateinit var rocketDatabase: RocketDatabase

    @Inject
    lateinit var rocketApi: RocketApi

    private val rocketLiveData = MutableLiveData<Response<List<Rocket>>>()

    val rockets: LiveData<Response<List<Rocket>>>
        get() = rocketLiveData

    private val rocketDetailLiveData = MutableLiveData<Response<Rocket>>()

    val rocketDetail: LiveData<Response<Rocket>>
        get() = rocketDetailLiveData

    suspend fun getRockets() {
        if (NetworkUtils.isInternetConnected(context)) {

            try {
                val result = rocketApi.getRockets()
                if (result?.body() != null) {
                    rocketDatabase.rocketDao().addRocket(result.body()!!)
                    rocketLiveData.postValue(Response.Success(result.body()))
                }
            } catch (e: Exception) {
                rocketLiveData.postValue(Response.Error(e.message.toString()))
            }

        } else {
            val rockets = rocketDatabase.rocketDao().getRockets()
            rocketLiveData.postValue(Response.Success(rockets))
        }

    }

    suspend fun getRocketDetail(id: String) {
        if (NetworkUtils.isInternetConnected(context)) {
            try {
                val result = rocketApi.getRocketDetail(id)
                if (result?.body() != null) {
                    rocketDetailLiveData.postValue(Response.Success(result.body()))
                }
            } catch (e: Exception) {
                rocketDetailLiveData.postValue(Response.Error(e.message.toString()))
            }

        } else {
            val rockets = rocketDatabase.rocketDao().getRocketDetail(id)
            rocketDetailLiveData.postValue(Response.Success(rockets))
        }

    }

    suspend fun getRocketBackground() {
        val result = rocketApi.getRockets()
        if (result?.body() != null) {
            rocketDatabase.rocketDao().addRocket(result.body()!!)
        }
    }
}