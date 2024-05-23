package com.example.wicrypttechnicaltest.api

import com.example.crudtestapplication.model.Jobs
import com.google.gson.annotations.SerializedName

data class JobSearchResponse(
        @SerializedName("jobs") val jobs: List<Jobs> = emptyList(),
        val nextPage: Int? = null
)