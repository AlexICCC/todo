package org.stalexman.todoapp.ui

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import org.stalexman.todoapp.R

class Navigator(
    private val navController: NavController
) {

    fun navigateTo(@IdRes id: Int, args: Bundle? = null) {
        val navOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.slide_enter)
            .setExitAnim(R.anim.slide_exit)
            .setPopEnterAnim(R.anim.slide_pop_enter)
            .setPopExitAnim(R.anim.slide_pop_exit)
            .build()
        navController.navigate(id, args, navOptions)
    }

    fun goBack() {
        navController.popBackStack()
    }
}