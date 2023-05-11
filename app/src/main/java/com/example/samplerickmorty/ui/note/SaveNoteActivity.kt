package com.example.samplerickmorty.ui.note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.samplerickmorty.databinding.ActivitySaveNoteBinding
import com.example.samplerickmorty.db.NoteEntity
import com.example.samplerickmorty.ui.note.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaveNoteActivity : AppCompatActivity() {
    private var _binding:ActivitySaveNoteBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<NoteViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySaveNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.notes.observe(this){
            Log.d("Notes", "onCreate: $it")
        }

        binding.saveBttn.setOnClickListener {
            viewModel.saveNote(NoteEntity(note = binding.noteTxt.text.toString()))
            viewModel.getNotes()
        }
    }

}