package com.llandroidtest.presentation.ui.fragment

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class FragmentLifecycleObserver(private val onCreated: () -> Unit) : DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        onCreated()
    }
}