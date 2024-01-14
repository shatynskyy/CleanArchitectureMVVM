package com.example.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.models.BeerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BeerDao {

    @Query("SELECT * FROM beers")
    fun getBeers(): Flow<List<BeerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBeer(beer: BeerEntity)

    @Delete
    fun deleteBeer(beer: BeerEntity)
}