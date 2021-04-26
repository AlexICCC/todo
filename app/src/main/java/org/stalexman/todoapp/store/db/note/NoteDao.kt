package org.stalexman.todoapp.store.db.note

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY localId  DESC")
    suspend fun loadAll(): List<NoteEntity>

    @Query("SELECT * FROM notes WHERE localId=:id")
    suspend fun loadById(id: Long): NoteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(entity: NoteEntity)

    @Insert
    suspend fun insert(entity: NoteEntity)

    @Query("DELETE FROM notes")
    suspend fun clear()
}