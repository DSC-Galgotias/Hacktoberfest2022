package com.github.danishjamal104.notes.data.local

import com.github.danishjamal104.notes.data.model.Note
import com.github.danishjamal104.notes.data.model.User

interface CacheDataSource {

    suspend fun addUser(user: User): Long
    suspend fun getUser(userId: String): User
    suspend fun deleteUser(userId: String): Int
    suspend fun addNote(note: Note): Long
    suspend fun getNotes(userId: String): List<Note>
    suspend fun getNote(id: Int, userId: String): Note
    suspend fun updateNote(note: Note): Int
    suspend fun deleteNote(note: Note): Int
}