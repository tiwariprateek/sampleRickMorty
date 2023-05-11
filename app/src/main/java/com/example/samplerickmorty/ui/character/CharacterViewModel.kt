package com.example.samplerickmorty.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samplerickmorty.data.CharacterResponse
import com.example.samplerickmorty.repository.CharacterRepository
import com.example.samplerickmorty.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val characterRepository: CharacterRepository) : ViewModel() {

    val characterResponse : LiveData<NetworkResult<CharacterResponse>> get() = characterRepository.characterResponse

    init {
        viewModelScope.launch {
            withContext(IO){
                characterRepository.getCharacters()
            }
        }
    }
}