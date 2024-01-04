package com.example.data

import com.example.data.models.BeerDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("beers/random")
    suspend fun getRandomBeer() : Response<List<BeerDTO>>
}