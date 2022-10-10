package com.github.danishjamal104.notes.ui.fragment.home.adapter

import android.view.View

interface ItemClickListener<T> {
    fun onItemClicked(item: T, position: Int, view: View)
}