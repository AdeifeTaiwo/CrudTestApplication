package com.example.crudtestapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.crudtestapplication.data.JobSearchRepository

class ViewModelFactory2(private val repository: JobSearchRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavedJobSearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SavedJobSearchViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}