package com.example.samplerickmorty.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteEntity: NoteEntity)

    @Query("Select * from NotesDB ORDER BY id DESC")
    fun getNotes():MutableList<NoteEntity>


}