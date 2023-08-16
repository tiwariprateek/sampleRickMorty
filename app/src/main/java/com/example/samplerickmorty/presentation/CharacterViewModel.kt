package com.example.samplerickmorty.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samplerickmorty.data.CharacterResponse
import com.example.samplerickmorty.domain.repository.CharacterRepository
import com.example.samplerickmorty.repository.CharacterRepository
import com.example.samplerickmorty.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val characterRepository: CharacterRepository) : ViewModel() {

    private val _characterResponse = MutableStateFlow<NetworkResult<CharacterResponse>>(NetworkResult.Loading())
    val characterResponse : StateFlow<NetworkResult<CharacterResponse>> = _characterResponse


    init {
        viewModelScope.launch {
            withContext(IO){
                characterRepository.getCharacterListing()
                    .catch {
                        _characterResponse.value = NetworkResult.Error(it.message)
                    }
                    .collect{
                        _characterResponse.value = it
                    }
            }
        }
    }
}