package com.example.samplerickmorty.data.mapper

import com.example.samplerickmorty.data.local.CharacterListingEntity
import com.example.samplerickmorty.domain.model.CharacterListing

fun CharacterListingEntity.toCharacter() = CharacterListing(name, image)

fun CharacterListing.toCharacterEntity() = CharacterListingEntity(name, image)