package com.extra.edge.test.api

import com.extra.edge.test.model.Rocket
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface RocketApi {

    @GET("rockets")
    suspend fun getRockets(): Response<List<Rocket>>

    @GET("rockets/{id}")
    suspend fun getRocketDetail(@Path("id") id: String): Response<Rocket>
}