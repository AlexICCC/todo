package org.stalexman.todoapp.data

import org.stalexman.todoapp.store.db.note.NoteStore

class NotesRepository(
    private val store: NoteStore
) {

    private val listeners = mutableListOf<OnDataChanged>()

    suspend fun loadAll() = store.loadAll()

    suspend fun loadById(id: Long) = store.loadById(id)

    suspend fun insertOrUpdate(note: Note) {
        store.insertOrUpdate(note)
        notifyDataChanged()
    }

    suspend fun insert(note: Note) {
        store.insert(note)
        notifyDataChanged()
    }

    fun addListener(l: OnDataChanged) = listeners.add(l)
    fun removeListener(l: OnDataChanged) = listeners.remove(l)

    private fun notifyDataChanged() = listeners.forEach { it.onDataChanged() }

    interface OnDataChanged {
        fun onDataChanged()
    }
}