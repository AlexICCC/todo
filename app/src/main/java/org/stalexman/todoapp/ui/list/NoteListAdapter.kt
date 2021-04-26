package org.stalexman.todoapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import org.stalexman.todoapp.R
import org.stalexman.todoapp.data.Note

class NoteListAdapter(
    private val onItemClicked: (Note) -> Unit
) : RecyclerView.Adapter<NoteListViewHolder>() {

    var items: List<Note> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NoteListViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.card_note, parent, false) as CardView
        )

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) =
        holder.bind(items[position], onItemClicked)

    override fun getItemCount() = items.size
}