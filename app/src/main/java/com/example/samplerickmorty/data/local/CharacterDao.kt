package com.example.samplerickmorty.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterListing(
        characterListingEntity: List<CharacterListingEntity>
    )

    @Query("SELECT * from characterListingEntity")
    suspend fun getCharacter(): List<CharacterListingEntity>

    @Query("DELETE FROM characterListingEntity")
    suspend fun clearCharacterListing()
}