package com.example.cleanarchitecturemvvm.models

data class BeerModelPresentation(
    val id : Int,
    val name : String,
    val description : String,
    val image : String?,
    val abv : Double,
    val firstBrewed : String
)


