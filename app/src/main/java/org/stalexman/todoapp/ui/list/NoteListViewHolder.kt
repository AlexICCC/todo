package org.stalexman.todoapp.ui.list

import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import org.stalexman.todoapp.R
import org.stalexman.todoapp.data.Note

class NoteListViewHolder(
    cardView: CardView
): RecyclerView.ViewHolder(cardView) {
    private val subjectView = cardView.findViewById<TextView>(R.id.note_subject)
    private val textView = cardView.findViewById<TextView>(R.id.note_text)

    fun bind(item: Note, onItemClicked: (Note) -> Unit) {
        subjectView.text = item.subject
        textView.text = item.text
        itemView.setOnClickListener { onItemClicked(item) }
    }
}