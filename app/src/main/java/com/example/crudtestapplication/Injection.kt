package com.example.crudtestapplication

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.crudtestapplication.api.JobSearchService
import com.example.crudtestapplication.data.JobSearchRepository
import com.example.crudtestapplication.db.JobRepoDatabase
import com.example.crudtestapplication.ui.ViewModelFactory
import com.example.crudtestapplication.ui.ViewModelFactory2

import java.security.AccessControlContext

object Injection {

    private fun provideJobSearchRepository(context: Context): JobSearchRepository {
        return JobSearchRepository(JobSearchService.create(), JobRepoDatabase.getInstance(context) )

    }

    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory{
        return  ViewModelFactory(provideJobSearchRepository(context))
    }

    fun provideViewModelFactory2(context: Context): ViewModelProvider.Factory{
        return  ViewModelFactory2(provideJobSearchRepository(context))
    }

}