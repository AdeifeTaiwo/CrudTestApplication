package com.example.crudtestapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.crudtestapplication.data.JobSearchRepository
import com.example.wicrypttechnicaltest.ui.JobSearchViewModel


class ViewModelFactory(private val repository: JobSearchRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JobSearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JobSearchViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}