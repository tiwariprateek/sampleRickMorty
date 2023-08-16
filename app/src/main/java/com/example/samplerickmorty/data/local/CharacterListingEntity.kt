package com.example.samplerickmorty.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterListingEntity(
    val name:String,
    val image:String,
    @PrimaryKey val id:Int? = null
)