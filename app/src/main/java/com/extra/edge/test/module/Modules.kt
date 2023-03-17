package com.extra.edge.test.module

import android.content.Context
import com.extra.edge.test.api.RetrofitHelper
import com.extra.edge.test.api.RocketApi
import com.extra.edge.test.db.RocketDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class Modules {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): RocketDatabase {
        return RocketDatabase.getInstance(context)!!
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return RetrofitHelper.getInstance()
    }

    @Provides
    fun provideRocketApi(): RocketApi {
        return provideRetrofit().create(RocketApi::class.java)
    }

}