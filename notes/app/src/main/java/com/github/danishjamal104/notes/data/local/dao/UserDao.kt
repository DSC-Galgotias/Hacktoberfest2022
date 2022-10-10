package com.github.danishjamal104.notes.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.danishjamal104.notes.data.entity.cache.UserCacheEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userCacheEntity: UserCacheEntity): Long

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUser(userId: String): List<UserCacheEntity>

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteUser(userId: String): Int
}