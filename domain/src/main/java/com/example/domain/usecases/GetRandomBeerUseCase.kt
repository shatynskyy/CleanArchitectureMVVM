package com.example.domain.usecases

import com.example.domain.repository.Repository
import com.example.domain.models.BeerDomainModel

class GetRandomBeerUseCase(private val repository: Repository) {
    suspend operator fun invoke() : BeerDomainModel {
        return repository.getRandomBeer()
    }
}