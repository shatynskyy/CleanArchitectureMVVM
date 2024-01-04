package com.example.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class BeerDTO (
    val id : Int,
    val name : String,
    val tagline : String,
    @SerializedName("image_url")
    val image : String?,
    val abv : Double,
    @SerializedName("first_brewed")
    val firstBrewed : String
)

@Entity(tableName = "beers")
data class BeerEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val image: String?,
    val abv: Double,
    val firstBrewed: String
)