package org.stalexman.todoapp.ui.note

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import org.koin.android.viewmodel.ext.android.viewModel
import org.stalexman.todoapp.ui.BaseFragment
import org.stalexman.todoapp.R

class NoteFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_note
    override val titleId = R.string.fragment_note_title

    private val viewModel: NoteItemViewModel by viewModel()

    private lateinit var subjectView: EditText
    private lateinit var textView: EditText
    private lateinit var saveBtn: Button

    companion object {
        const val LOCAL_ID_ARG = "LOCAL_ID_ARG"
    }

    override fun initUI(view: View, savedInstanceState: Bundle?) {
        readArguments()

        subjectView = view.findViewById(R.id.note_item_subject)
        textView = view.findViewById(R.id.note_item_text)
        saveBtn = view.findViewById(R.id.note_item_save_btn)

        viewModel.subject.observe(this) { setTextValue(subjectView, it) }
        viewModel.text.observe(this) {setTextValue(textView, it) }

        subjectView.doOnTextChanged { text, _, _, _ ->
            viewModel.onSubjectChanged(text?.toString().orEmpty())
        }

        textView.doOnTextChanged { text, _, _, _ ->
            viewModel.onTextChanged(text?.toString().orEmpty())
        }

        saveBtn.setOnClickListener {
            viewModel.saveNote { navigator.goBack() }
        }
    }

    private fun readArguments() {
        val id = arguments?.getLong(LOCAL_ID_ARG) ?: return
        viewModel.loadNoteById(id)
    }

    private fun setTextValue(editText: EditText, value: String) {
        editText.setText(value)
        editText.setSelection(value.length)
    }
}