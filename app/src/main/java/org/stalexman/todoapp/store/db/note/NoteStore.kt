package org.stalexman.todoapp.store.db.note

import org.stalexman.todoapp.data.Note
import org.stalexman.todoapp.store.db.TodoDatabase

class NoteStore(database: TodoDatabase) {

    private val dao = database.employeeDao()

    suspend fun loadAll() = dao.loadAll().map { it.toNote() }

    suspend fun loadById(id: Long) = dao.loadById(id)?.toNote()

    suspend fun insertOrUpdate(item: Note) = dao.insertOrUpdate(NoteEntity(item))

    suspend fun insert(item: Note) = dao.insertOrUpdate(NoteEntity(item))

    suspend fun clear() = dao.clear()
}