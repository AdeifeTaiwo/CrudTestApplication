package com.example.crudtestapplication.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.crudtestapplication.api.JobSearchService
import com.example.crudtestapplication.db.JobRepoDatabase
import com.example.crudtestapplication.db.RemoteKeys
import com.example.crudtestapplication.model.Jobs
import kotlinx.coroutines.Job
import retrofit2.HttpException
import java.io.IOException

private const val GITHUB_STARTING_PAGE_INDEX = 1

@ExperimentalPagingApi
class JobSearchRemoteMediator(
    private val query: String,
    private val service: JobSearchService,
    private val repoDatabase: JobRepoDatabase

) : RemoteMediator<Int, Jobs>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Jobs>): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: GITHUB_STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKeys = remoteKeys?.prevKey
                if (prevKeys == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                prevKeys
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                if (nextKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                nextKey
            }
        }
        val apiQuery = query
        try {
            val sources = listOf("bbc-news", "abc-news", "al-jazeera-english").joinToString{","}
            val apiResponse = service.jobSearchRepos(page, sources = "bbc-news")
            print(apiResponse)
            val repos = apiResponse.articles
            print("totalSize" + repos.size);
            val endOfPagination = repos.isEmpty()

            repoDatabase.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    //repoDatabase.remoteKeysDao().clearRemoteKeys()
                    //repoDatabase.reposDao().clearRepos()
                }
                val prevKey = if (page == GITHUB_STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPagination) null else page + 1

                print("totalSize" + repos.size);
                val keys = repos.map {
                    RemoteKeys(
                            repoId = it.created_at?:"",
                            nextKey = nextKey,
                            prevKey = prevKey
                    )
                }
                val repo = repos.map {
                    Jobs(
                        id = it.id,
                        author = it.author,
                        url = it.url,
                        created_at = it.created_at,
                        description = it.description,
                        publishedAt = it.publishedAt,

                        urlToImage = it.urlToImage,
                        isChecked = it.isChecked,
                        applied = it.applied,
                        companyName = it.companyName,
                        company_logo = it.company_logo,
                        location = it.location,
                        job_title = it.job_title,
                        company_url = it.company_url,
                        how_to_apply = it.how_to_apply
                    )
                }
                try {
                    repoDatabase.remoteKeysDao().insertAll(keys)
                    repoDatabase.reposDao().insertAll(repo)
                }
                catch (e: Exception){
                    print(e.stackTrace);
                }
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPagination)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Jobs>): RemoteKeys? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
                ?.let { repo ->
                    // Get the remote keys of the last item retrieved
                    repoDatabase.remoteKeysDao().remoteKeysRepoId(repo.created_at?:"")
                }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Jobs>): RemoteKeys? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
                ?.let { repo ->
                    // Get the remote keys of the first items retrieved
                    repoDatabase.remoteKeysDao().remoteKeysRepoId(repo.created_at?:"")
                }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
            state: PagingState<Int, Jobs>
    ): RemoteKeys? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.created_at?.let { repoId ->
                repoDatabase.remoteKeysDao().remoteKeysRepoId(repoId)
            }
        }
    }


}