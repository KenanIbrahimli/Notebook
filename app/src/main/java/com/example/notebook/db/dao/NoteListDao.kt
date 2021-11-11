package com.example.notebook.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NoteListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataToDb(noteListEntity: NoteListEntity): Long

    @Update
    suspend fun updateDatainDb(noteListEntity: NoteListEntity)

    @Delete
    suspend fun deleteDataToDb(noteListEntity: NoteListEntity)

    @Query("SELECT * FROM note_list_entity_table")
    fun loadAllItems(): LiveData<List<NoteListEntity>>


    @Query("SELECT * FROM note_list_entity_table WHERE noteId LIKE :noteid")
    fun selectNote(noteid: String): NoteListEntity
}