package com.example.domain.models

data class BeerDomainModel (
    val id : Int,
    val name : String,
    val description : String,
    val image : String,
    val abv : Double,
    val firstBrewed : String
)