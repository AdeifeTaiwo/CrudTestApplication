package com.example.crudtestapplication.data

import android.app.DownloadManager
import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.crudtestapplication.api.JobSearchService
import com.example.crudtestapplication.db.JobRepoDatabase
import com.example.crudtestapplication.model.Jobs

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge

class JobSearchRepository (
    private val service: JobSearchService,
    private val database: JobRepoDatabase
) {



    @ExperimentalPagingApi
    fun getSearchResultStream(query: String): Flow<PagingData<Jobs>> {
        Log.d("JobSearhRepository", "New query: $query")
        val dbQuery = "%${query.replace(' ', '%')}%"
        val pagingSourceFactory = { database.reposDao().reposByName()}
        return Pager(
                config = PagingConfig(
                        pageSize = NETWORK_PAGE_SIZE,
                        enablePlaceholders = false,
                        initialLoadSize = NETWORK_PAGE_SIZE

                ),
                remoteMediator = JobSearchRemoteMediator(
                        query,
                        service,
                        database
                ),
                pagingSourceFactory = pagingSourceFactory

        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 50
    }
    fun getSavedSearch() : Flow<PagingData<Jobs>>{
        val pagingSourceFactory = { database.reposDao().reposByCheckFavourite() }
        return Pager(
                config = PagingConfig(
                        pageSize = NETWORK_PAGE_SIZE,
                        enablePlaceholders = false,
                        initialLoadSize = NETWORK_PAGE_SIZE

                ),

                pagingSourceFactory = pagingSourceFactory

        ).flow
    }
}