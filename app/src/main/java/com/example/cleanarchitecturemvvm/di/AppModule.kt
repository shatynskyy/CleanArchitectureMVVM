package com.example.cleanarchitecturemvvm.di

import com.example.domain.usecases.GetRandomBeerUseCase
import com.example.domain.repository.Repository
import com.example.domain.usecases.DeleteBeerUseCase
import com.example.domain.usecases.GetBeersUseCase
import com.example.domain.usecases.InsertBeerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    fun provideGetRandomBeerUseCase(repository: Repository): GetRandomBeerUseCase {
        return GetRandomBeerUseCase(repository = repository)
    }

    @Provides
    fun provideInsertBeerUseCase(repository: Repository): InsertBeerUseCase {
        return InsertBeerUseCase(repository = repository)
    }

    @Provides
    fun provideBeersUseCase(repository: Repository): GetBeersUseCase {
        return GetBeersUseCase(repository = repository)
    }

    @Provides
    fun provideDeleteBeersUseCase(repository: Repository): DeleteBeerUseCase {
        return DeleteBeerUseCase(repository = repository)
    }

}