package com.example.firstnetworkapi.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object to access the network service
 *
 * ONLY one instance of this NETWORK will be created
 */
object Network {

    /**
     * Creating retrofit object using Builder
     */
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ServiceApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val SATRetrofit: Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(ServiceApi.SATBASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }




    /**
     * Creating my [ServiceApi]
     */
    val serviceApi by lazy {
        retrofit.create(ServiceApi::class.java)
    }
}