package com.stslex.core_data_source

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stslex.core_model.model.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table ORDER BY timestamp DESC")
    fun getAllNotes(): PagingSource<Int, NoteEntity>

    @Query("SELECT * FROM note_table")
    fun getAllNotesRow(): List<NoteEntity>

    @Query("SELECT * FROM note_table")
    fun getAllNotesFlow(): Flow<List<NoteEntity>>

    @Query("SELECT COUNT(*) FROM note_table")
    fun getNotesSize(): Flow<Int>

    @Query("SELECT * FROM note_table WHERE id=:id")
    suspend fun getNoteById(id: Int): NoteEntity

    @Query("DELETE FROM note_table")
    suspend fun deleteAllNotes()

    @Query("DELETE FROM note_table WHERE id IN(:ids)")
    suspend fun deleteNotesById(ids: List<Int>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNotes(noteList: List<NoteEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)
}