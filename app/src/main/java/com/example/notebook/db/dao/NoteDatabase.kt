package com.example.notebook.db.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [NoteListEntity::class], version = 1)
abstract class NoteDatabase: RoomDatabase(){
    abstract fun noteDao(): NoteListDao

    companion object{
        private var INSTANCE: NoteDatabase? = null

        fun getInstrance(context: Context):NoteDatabase =
            INSTANCE?: synchronized(this) {
                INSTANCE ?: buildDataBase(context).also { INSTANCE = it}
            }

        fun buildDataBase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                "note_list_database"
            ).build()
    }
}

