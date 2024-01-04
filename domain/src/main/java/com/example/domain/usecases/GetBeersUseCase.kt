package com.example.domain.usecases

import com.example.domain.models.BeerDomainModel
import com.example.domain.repository.Repository

class GetBeersUseCase(private val repository: Repository) {
    suspend operator fun invoke() : List<BeerDomainModel> {
        return repository.getBeers()
    }
}