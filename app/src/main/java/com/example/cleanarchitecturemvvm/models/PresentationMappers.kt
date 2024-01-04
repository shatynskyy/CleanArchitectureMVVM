package com.example.cleanarchitecturemvvm.models

import com.example.domain.models.BeerDomainModel

fun BeerDomainModel.toBeerPresentation(): BeerModelPresentation {
    return BeerModelPresentation(
        id = id,
        name = name,
        description = description,
        image = image ?: "",
        abv = abv,
        firstBrewed = firstBrewed
    )
}

fun BeerModelPresentation.toBeerDomain(): BeerDomainModel {
    return BeerDomainModel(
        id = id,
        name = name,
        description = description,
        image = image ?: "",
        abv = abv,
        firstBrewed = firstBrewed
    )
}