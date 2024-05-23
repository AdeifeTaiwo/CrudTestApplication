package com.example.crudtestapplication.ui

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.crudtestapplication.model.Jobs
import kotlinx.coroutines.Job

class JobSearchAdapter : PagingDataAdapter<Jobs, JobRepoViewHolder>(REP0_COMPANION){


    override fun onBindViewHolder(holder: JobRepoViewHolder, position: Int) {
        val jobs = getItem(position)
        if(jobs != null) {
            holder.bind(jobs)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobRepoViewHolder {
        return JobRepoViewHolder.create(parent)

    }

    companion object REP0_COMPANION : DiffUtil.ItemCallback<Jobs>() {
        override fun areItemsTheSame(oldItem: Jobs, newItem: Jobs): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Jobs, newItem: Jobs): Boolean {
            return oldItem.created_at == newItem.created_at
        }
    }


}


