package com.example.notebook.db.repository

import com.example.notebook.db.dao.NoteListDao
import com.example.notebook.db.dao.NoteListEntity

class NoteRepository(private val noteListDao: NoteListDao) {

    var note = noteListDao.loadAllItems()

    suspend fun insertNotes(noteListEntity: NoteListEntity){
        noteListDao.insertDataToDb(noteListEntity)
    }

    suspend fun updateNotes(noteListEntity: NoteListEntity){
        noteListDao.updateDatainDb(noteListEntity)
    }

    suspend fun deleteNotes(noteListEntity: NoteListEntity){
        noteListDao.deleteDataToDb(noteListEntity)
    }

}