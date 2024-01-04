package com.example.data

import com.example.data.exceptions.GeneralException
import com.example.data.models.toBeerDomain
import com.example.data.models.toBeerEntity
import com.example.domain.models.BeerDomainModel
import com.example.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService, private val beerDao: BeerDao) : Repository {

    override suspend fun getRandomBeer(): BeerDomainModel {
        val response = apiService.getRandomBeer()
        return response.body()?.firstOrNull()?.toBeerDomain() ?: throw GeneralException(response.message())
    }

    override suspend fun insertBeer(beer: BeerDomainModel) {
        beerDao.insertBeer(beer.toBeerEntity())
    }

    override suspend fun deleteBeer(beer: BeerDomainModel) {
        beerDao.deleteBeer(beer.toBeerEntity())
    }

    override suspend fun getBeers(): List<BeerDomainModel> {
        return beerDao.getBeers().map { it.toBeerDomain() }
    }
}