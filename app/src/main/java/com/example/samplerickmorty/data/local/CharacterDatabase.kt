package com.example.samplerickmorty.data.local

import androidx.room.Database

@Database(entities = [CharacterListingEntity::class], version = 1)
abstract class CharacterDatabase() {
    abstract val dao :CharacterDao
}