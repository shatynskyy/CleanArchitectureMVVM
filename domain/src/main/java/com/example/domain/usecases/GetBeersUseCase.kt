package com.example.domain.usecases

import com.example.domain.models.BeerDomainModel
import com.example.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetBeersUseCase(private val repository: Repository) {
    suspend operator fun invoke(): Flow<List<BeerDomainModel>> {
        return repository.getBeers()
    }
}