package com.example.samplerickmorty.presentation

import com.example.samplerickmorty.domain.model.CharacterListing

data class CharacterListingStae(
    val character:List<CharacterListing> = emptyList(),
    val isLoading:Boolean = false,
    val isRefreshing:Boolean = false
)
