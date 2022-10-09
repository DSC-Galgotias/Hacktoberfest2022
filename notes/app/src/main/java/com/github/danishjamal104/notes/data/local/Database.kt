package com.github.danishjamal104.notes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.danishjamal104.notes.data.entity.cache.NoteCacheEntity
import com.github.danishjamal104.notes.data.entity.cache.UserCacheEntity
import com.github.danishjamal104.notes.data.local.dao.NoteDao
import com.github.danishjamal104.notes.data.local.dao.UserDao

@Database(entities = [NoteCacheEntity::class, UserCacheEntity::class], version = 1)
abstract class Database: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun noteDao(): NoteDao
}