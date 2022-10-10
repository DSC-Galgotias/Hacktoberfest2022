package com.github.danishjamal104.notes.data.repository.note

import com.github.danishjamal104.notes.data.local.CacheDataSource
import com.github.danishjamal104.notes.data.model.Note
import com.github.danishjamal104.notes.util.ServiceResult
import com.github.danishjamal104.notes.util.sharedpreference.UserPreferences
import java.lang.Exception
import java.util.*

class NotesRepositoryImpl
constructor(
    private val cacheDataSource: CacheDataSource,
    private val userPreferences: UserPreferences
): NotesRepository{

    private val userId get() = userPreferences.getUserId()


    override suspend fun createNote(noteText: String): ServiceResult<Unit> {
        val note = Note(-1, userId, noteText, Date().time)
        return try {
            cacheDataSource.addNote(note)
            ServiceResult.Success(Unit)
        } catch (e: Exception) {
            ServiceResult.Error(""+e.localizedMessage)
        }
    }

    override suspend fun getNotes(): ServiceResult<List<Note>> {
        return try {
            val result = cacheDataSource.getNotes(userId)
            ServiceResult.Success(result)
        } catch (e: Exception) {
            ServiceResult.Error(""+e.localizedMessage)
        }
    }

    override suspend fun getNote(noteId: Int): ServiceResult<Note> {
        return try {
            val result = cacheDataSource.getNote(noteId, userId)
            ServiceResult.Success(result)
        } catch (e: Exception) {
            ServiceResult.Error(""+e.localizedMessage)
        }
    }

    override suspend fun updateNote(note: Note): ServiceResult<Note> {
        return try {
            when(cacheDataSource.updateNote(note)) {
                0 -> ServiceResult.Error("Updating failed ")
                1 -> ServiceResult.Success(note)
                else -> ServiceResult.Error("Invalid update")
            }
        } catch (e: Exception) {
            ServiceResult.Error(""+e.localizedMessage)
        }
    }

    override suspend fun deleteNote(note: Note): ServiceResult<Note> {
        return try {
            when(cacheDataSource.deleteNote(note)) {
                0 -> ServiceResult.Error("Deletion failed ")
                1 -> ServiceResult.Success(note)
                else -> ServiceResult.Error("Invalid deletion")
            }
        } catch (e: Exception) {
            ServiceResult.Error(""+e.localizedMessage)
        }
    }
}