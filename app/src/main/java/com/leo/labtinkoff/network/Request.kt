package com.leo.labtinkoff.network

import com.leo.labtinkoff.model.RandomDataGifs
import retrofit2.http.GET

interface Request {

    @GET(Api.randomGifs)
    suspend fun getRandomGifs():RandomDataGifs

}