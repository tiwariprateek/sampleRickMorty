package com.example.samplerickmorty.ui.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samplerickmorty.db.NoteEntity
import com.example.samplerickmorty.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository):ViewModel() {

    private val _notes = MutableLiveData<MutableList<NoteEntity>>()
    val notes:LiveData<MutableList<NoteEntity>> get() = _notes

    init {
        getNotes()
    }

    fun saveNote(noteEntity: NoteEntity){
        noteRepository.saveNote(noteEntity)
    }

    fun getNotes(){
        _notes.postValue(noteRepository.getNotes())
    }

}