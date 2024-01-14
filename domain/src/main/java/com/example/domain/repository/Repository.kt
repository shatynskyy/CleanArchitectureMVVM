package com.example.domain.repository

import com.example.domain.models.BeerDomainModel
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getRandomBeer(): BeerDomainModel

    suspend fun insertBeer(beer: BeerDomainModel)

    suspend fun deleteBeer(beer: BeerDomainModel)

    suspend fun getBeers(): Flow<List<BeerDomainModel>>
}