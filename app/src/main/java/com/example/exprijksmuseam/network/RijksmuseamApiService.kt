package com.example.exprijksmuseam.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL: String = "https://www.rijksmuseum.nl/api/nl/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface RijksmuseamApiService {
    @GET("collection")
    fun getAll(@Query("key") key: String, @Query("toppieces") topPieces: Boolean):
            Call<String>
}

object RijksmuseamApi {
    val retrofitService: RijksmuseamApiService by lazy {
        retrofit.create(RijksmuseamApiService::class.java)
    }
}