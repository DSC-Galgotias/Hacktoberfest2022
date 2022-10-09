package com.github.danishjamal104.notes.data.local.dao

import androidx.room.*
import com.github.danishjamal104.notes.data.entity.cache.NoteCacheEntity

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertNote(noteCacheEntity: NoteCacheEntity): Long

    @Query("SELECT * FROM notes WHERE userId = :userId ORDER BY timestamp")
    suspend fun getNotes(userId: String): List<NoteCacheEntity>

    @Query("SELECT * FROM notes WHERE userId = :userId AND id = :id")
    suspend fun getNote(id: Int, userId: String): List<NoteCacheEntity>

    @Update
    suspend fun updateNote(noteCacheEntity: NoteCacheEntity): Int

    @Delete
    suspend fun deleteNote(noteCacheEntity: NoteCacheEntity): Int
}