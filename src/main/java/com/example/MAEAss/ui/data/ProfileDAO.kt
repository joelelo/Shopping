package com.example.MAEAss.ui.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProfileDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(profile: Profile)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(profile: Profile)

    @Query("SELECT * FROM Profile LIMIT 1")
    fun getProfile(): LiveData<Profile>
}