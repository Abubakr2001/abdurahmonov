package com.leo.labtinkoff.repository

import com.leo.labtinkoff.model.RandomDataGifs
import com.leo.labtinkoff.network.Request
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val service:Request){

    suspend fun getRandomGifs():RandomDataGifs{
        return service.getRandomGifs()
    }

}