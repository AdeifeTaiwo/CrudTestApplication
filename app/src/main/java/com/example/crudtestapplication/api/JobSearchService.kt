package com.example.crudtestapplication.api

import com.example.crudtestapplication.api.JobSearchService.Constants.API_KEY
import com.example.crudtestapplication.model.JobRepo
import com.example.crudtestapplication.model.Jobs
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.GET
import retrofit2.http.Query
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

interface JobSearchService {

    //https://remotive.io/api/remote-jobs?search=front%20end
    //https://jobs.github.com/positions.json?page=1&search=code

@GET("everything")
suspend fun jobSearchRepos(
    @Query("page") page: Int,
    @Query("sources") sources:String,
    @Query("apiKey") apiKey: String = API_KEY

) : JobRepo


    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"

        fun create(): JobSearchService{
            val logger = HttpLoggingInterceptor()
            logger.level = Level.BODY

            val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()
            return  Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(JobSearchService::class.java)
        }
    }

    object Constants {
        const val USER_SETTINGS = "userSettings"
        const val APP_ENTRY = "appEntry"

        const val API_KEY = "03adb9d7210e4867a56ceff36703a0bf"
        const val BASE_URL = "https://newsapi.org/v2/"
    }
}

