package org.stalexman.todoapp.ui

import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar

class ToolbarController(
    val toolbar: Toolbar
) {

    fun setTitle(@StringRes titleId: Int) = toolbar.setTitle(titleId)

    fun setTitle(title: String) {
        toolbar.title = title
    }
}