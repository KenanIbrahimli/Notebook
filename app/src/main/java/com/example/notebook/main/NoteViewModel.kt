package com.example.notebook.main

import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import com.example.notebook.db.repository.NoteRepository


class NoteViewModel (repository: NoteRepository) : ViewModel(), Observable {
    var notes  = repository.note

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

}