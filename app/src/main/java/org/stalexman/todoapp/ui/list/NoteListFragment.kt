package org.stalexman.todoapp.ui.list

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.android.viewmodel.ext.android.viewModel
import org.stalexman.todoapp.ui.BaseFragment
import org.stalexman.todoapp.R
import org.stalexman.todoapp.data.Note
import org.stalexman.todoapp.ui.note.NoteFragment.Companion.LOCAL_ID_ARG

class NoteListFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_note_list
    override val titleId = R.string.fragment_note_list_title

    private val viewModel: NoteListViewModel by viewModel()

    private val adapter = NoteListAdapter { editNote(it) }

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton

    override fun initUI(view: View, savedInstanceState: Bundle?) {

        recyclerView = view.findViewById(R.id.notes_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        fab = view.findViewById(R.id.fab)
        fab.setOnClickListener { addNote() }

        viewModel.notes.observe(viewLifecycleOwner) { setItems(it) }
    }

    private fun setItems(items: List<Note>) {
        adapter.items = items
        adapter.notifyDataSetChanged()
    }

    private fun editNote(note: Note) =
        navigator.navigateTo(
            R.id.note_fragment,
            bundleOf(LOCAL_ID_ARG to note.localId)
        )

    private fun addNote() =
        navigator.navigateTo(R.id.note_fragment)
}