package org.stalexman.todoapp.data


open class Note (
    val text: String,
    val subject: String = "",
    val createDate: Long = System.currentTimeMillis(),
    val modifiedDate: Long = System.currentTimeMillis(),
    open val localId: Long = 0,
)