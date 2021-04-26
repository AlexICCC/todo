package org.stalexman.todoapp.ui.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.stalexman.todoapp.data.Note
import org.stalexman.todoapp.data.NotesRepository

class NoteItemViewModel(
    private val notesRepository: NotesRepository
) : ViewModel() {

    private val _subject: MutableLiveData<String> = MutableLiveData("")
    val subject: LiveData<String> get() = _subject

    private val _text: MutableLiveData<String> = MutableLiveData("")
    val text: LiveData<String> get() = _text

    private var oldNote: Note? = null

    fun loadNoteById(localId: Long) {
        viewModelScope.launch {
            val note = notesRepository.loadById(localId) ?: return@launch
            _subject.postValue(note.subject)
            _text.postValue(note.text)
            oldNote = note
        }
    }

    fun onSubjectChanged(newSubject: String) {
        if (_subject.value == newSubject) return
        _subject.postValue(newSubject)
    }

    fun onTextChanged(newText: String) {
        if (_text.value == newText) return
        _text.postValue(newText)
    }

    fun saveNote(callback: () -> Unit) {
        if (_subject.value.isNullOrEmpty() && _text.value.isNullOrEmpty()) {
            callback()
            return
        }

        val oldNoteFinal = oldNote

        val createTime =
            if (oldNoteFinal == null) System.currentTimeMillis()
            else oldNoteFinal.createDate

        val modifiedTime = System.currentTimeMillis()
        val localId = oldNoteFinal?.localId ?: 0

        val newNote = Note(
            subject = _subject.value.orEmpty(),
            text = _text.value.orEmpty(),
            createDate = createTime,
            modifiedDate = modifiedTime,
            localId = localId
        )

        viewModelScope.launch {
            if (oldNoteFinal == null) {
                notesRepository.insert(newNote)
            } else {
                notesRepository.insertOrUpdate(newNote)
            }
            callback()
        }
    }
}