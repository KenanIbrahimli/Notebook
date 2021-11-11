package com.example.notebook.main.second

import android.app.Application
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.notebook.db.dao.NoteDatabase
import com.example.notebook.db.repository.NoteRepository
import com.example.notebook.db.dao.NoteListEntity
import kotlinx.coroutines.*

class NoteEditViewModel(application: Application) : AndroidViewModel(application), Observable  {

    val dao = NoteDatabase.getInstrance(application).noteDao()
    private val repository = NoteRepository(dao)

    var inputTitle: String = ""
    var inputDescription:String = ""
    var inputNoteId: Int = 0

    fun saveAndUpdateButton(title: String?, description: String?) {
        inputTitle = title!!
        inputDescription = description!!

        if (inputNoteId != 0) {
            update(NoteListEntity(inputNoteId, inputTitle, inputDescription))
        }
        else insert(NoteListEntity(0, inputTitle, inputDescription))

        inputTitle = ""
        inputDescription = ""
        inputNoteId = 0
    }

    fun insert(noteWithEntity: NoteListEntity) = viewModelScope.launch { repository.insertNotes(noteWithEntity) }

    fun update(noteListEntity: NoteListEntity) = viewModelScope.launch { repository.updateNotes(noteListEntity) }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    fun findById(id: String?) {
        inputNoteId = if (id.isNullOrBlank()) 0 else id.toInt()
    }


}