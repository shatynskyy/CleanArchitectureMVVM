package com.example.domain.repository

import com.example.domain.models.BeerDomainModel

interface Repository {

    suspend fun getRandomBeer(): BeerDomainModel

    suspend fun insertBeer(beer: BeerDomainModel)

    suspend fun deleteBeer(beer: BeerDomainModel)

    suspend fun getBeers(): List<BeerDomainModel>
}