package com.stslex.cnotes.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stslex.cnotes.data.entity.NoteEntity

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table ORDER BY timestamp")
    fun getAllNotes(): PagingSource<Int, NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNotes(noteList: List<NoteEntity>)

    @Query("DELETE FROM note_table")
    suspend fun deleteAllNotes()

    @Query("DELETE FROM note_table WHERE id IN(:ids)")
    suspend fun deleteNotesById(ids: List<Int>)
}