package com.example.crudtestapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.crudtestapplication.data.JobSearchRepository
import com.example.crudtestapplication.model.Jobs

import kotlinx.coroutines.flow.Flow

class SavedJobSearchViewModel(private val repository: JobSearchRepository) : ViewModel() {
    @ExperimentalPagingApi
    fun getFavourite(): Flow<PagingData<Jobs>> {
        return repository.getSavedSearch()
                .cachedIn(viewModelScope)
    }
}