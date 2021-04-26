package org.stalexman.todoapp.store.db.note

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.stalexman.todoapp.data.Note

@Entity(tableName = "notes")
class NoteEntity(
    val text: String,
    val subject: String,
    val createDate: Long,
    val modifiedDate: Long,
    @PrimaryKey(autoGenerate = true)
    val localId: Long
) {

    fun toNote() = Note(
        text,
        subject,
        createDate,
        modifiedDate,
        localId
    )

    constructor(item: Note) : this(
        item.text,
        item.subject,
        item.createDate,
        item.modifiedDate,
        item.localId,
    )
}