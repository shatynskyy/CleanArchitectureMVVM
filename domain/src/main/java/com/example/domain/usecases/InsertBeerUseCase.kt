package com.example.domain.usecases

import com.example.domain.models.BeerDomainModel
import com.example.domain.repository.Repository

class InsertBeerUseCase(private val repository: Repository) {
    suspend operator fun invoke(beer: BeerDomainModel) {
        return repository.insertBeer(beer = beer)
    }
}