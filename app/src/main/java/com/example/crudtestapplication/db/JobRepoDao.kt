package com.example.crudtestapplication.db

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.*
import com.example.crudtestapplication.model.Jobs
import kotlinx.coroutines.flow.Flow

@Dao
interface JobRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<Jobs>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(repos: Jobs)

    @Query("SELECT * FROM repos")
    fun reposByName(): PagingSource<Int, Jobs>

    @Query("SELECT * FROM repos WHERE isChecked = 1")
    fun reposByCheckFavourite(): PagingSource<Int, Jobs>

    @Query("SELECT * from repos WHERE created_at = :key")
    fun getJobsSearchWithId(key: String): Jobs

    @Query("DELETE FROM repos")
    suspend fun clearRepos()
}