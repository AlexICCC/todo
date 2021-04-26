package org.stalexman.todoapp.ui.list

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.stalexman.todoapp.data.Note
import org.stalexman.todoapp.data.NotesRepository

class NoteListViewModel(
    private val notesRepository: NotesRepository,
    private val context: Context
) : ViewModel() {

    private val _notes: MutableLiveData<List<Note>> = MutableLiveData(emptyList())
    val notes: LiveData<List<Note>> = _notes

    private val notesListener = object : NotesRepository.OnDataChanged {
        override fun onDataChanged() = loadNotes()
    }

    init {
        loadNotes()
        notesRepository.addListener(notesListener)
    }

    private fun loadNotes() {
        viewModelScope.launch {
            _notes.postValue(notesRepository.loadAll())
        }
    }

    override fun onCleared() {
        super.onCleared()
        notesRepository.removeListener(notesListener)
    }
}