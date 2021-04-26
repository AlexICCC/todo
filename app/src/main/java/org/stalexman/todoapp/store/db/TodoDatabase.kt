package org.stalexman.todoapp.store.db

import androidx.room.Database
import androidx.room.RoomDatabase
import org.stalexman.todoapp.store.db.note.NoteDao
import org.stalexman.todoapp.store.db.note.NoteEntity

@Database(
    entities = [
        NoteEntity::class
    ],
    version = 1
)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun employeeDao(): NoteDao;
}