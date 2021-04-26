package org.stalexman.todoapp

import androidx.room.Room
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.stalexman.todoapp.data.NotesRepository
import org.stalexman.todoapp.store.db.TodoDatabase
import org.stalexman.todoapp.store.db.note.NoteStore
import org.stalexman.todoapp.ui.list.NoteListViewModel
import org.stalexman.todoapp.ui.note.NoteItemViewModel

val KOIN_VIEW_MODELS = module {
    single { Room.databaseBuilder(get(), TodoDatabase::class.java, "database").build() }
    single { NoteStore(get()) }
    single { NotesRepository(get()) }

    viewModel { NoteListViewModel(get(), get()) }
    viewModel { NoteItemViewModel(get()) }
}