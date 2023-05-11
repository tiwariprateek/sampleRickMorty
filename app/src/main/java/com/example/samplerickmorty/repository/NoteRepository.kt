package com.example.samplerickmorty.repository

import com.example.samplerickmorty.db.NoteDao
import com.example.samplerickmorty.db.NoteEntity
import javax.inject.Inject

class NoteRepository @Inject constructor(private val dao: NoteDao) {

    fun saveNote(note: NoteEntity) = dao.insertNote(note)
    fun getNotes() = dao.getNotes()
}