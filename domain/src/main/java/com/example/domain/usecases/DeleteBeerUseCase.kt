package com.example.domain.usecases

import com.example.domain.models.BeerDomainModel
import com.example.domain.repository.Repository

class DeleteBeerUseCase (private val repository: Repository) {
    suspend operator fun invoke(beer: BeerDomainModel) {
        return repository.deleteBeer(beer = beer)
    }
}