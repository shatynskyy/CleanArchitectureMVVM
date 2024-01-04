package com.example.data.models

import com.example.domain.models.BeerDomainModel

fun BeerEntity.toBeerDomain(): BeerDomainModel {
    return BeerDomainModel(
        id = id,
        name = name,
        description = description,
        image = image ?: "",
        abv = abv,
        firstBrewed = firstBrewed
    )
}

fun BeerDomainModel.toBeerEntity(): BeerEntity {
    return BeerEntity(
        id = id,
        name = name,
        description = description,
        image = image,
        abv = abv,
        firstBrewed = firstBrewed
    )
}

fun BeerDTO.toBeerDomain(): BeerDomainModel {
    return BeerDomainModel(
        id = id,
        name = name,
        description = tagline,
        image = image ?: "",
        abv = abv,
        firstBrewed = firstBrewed
    )
}