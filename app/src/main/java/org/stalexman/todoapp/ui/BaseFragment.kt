package org.stalexman.todoapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import org.stalexman.todoapp.R

abstract class BaseFragment : Fragment() {

    abstract val layoutId: Int
    abstract val titleId: Int

    protected lateinit var navigator: Navigator
    protected lateinit var toolbar: ToolbarController

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = activity
        if (activity != null) {
            val toolbarView = activity.findViewById<Toolbar>(R.id.toolbar)
            toolbar = ToolbarController(toolbarView)
            toolbar.setTitle(titleId)
            navigator = Navigator(Navigation.findNavController(activity, R.id.nav_host_fragment))
        }
        val view = inflater.inflate(layoutId, container, false)
        initUI(view, savedInstanceState)
        return view
    }

    @CallSuper
    override fun onDestroyView() {
        destroyUI()
        super.onDestroyView()
    }

    abstract fun initUI(view: View, savedInstanceState: Bundle?)
    open fun destroyUI() {}
}