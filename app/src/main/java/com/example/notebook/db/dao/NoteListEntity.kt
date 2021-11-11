package com.example.notebook.db.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note_list_entity_table")
data class NoteListEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "noteId")
    val noteId: Int,

    @ColumnInfo(name = "noteTitle")
    val noteTitle: String,

    @ColumnInfo(name = "noteDescription")
    val noteDescription: String,

)