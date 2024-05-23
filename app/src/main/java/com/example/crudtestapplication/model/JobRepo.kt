package com.example.crudtestapplication.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import retrofit2.http.Field


data class JobRepo(
    val articles: List<Jobs>,
    val status: String,
    val totalResults: Int,
) {

}


@Entity(tableName = "repos")
data class Jobs(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    //@PrimaryKey @field:SerializedName("id") val id: String,
     val type: String? ="",
    val location: String? ="",
    @field:SerializedName("title") val created_at: String? ="",
    val companyName: String,
   val company_url: String?,
    val job_title: String?,
    val company_logo: String?,
     val how_to_apply: String,

    val author: String? ="",
    val content: String?="",
    val description: String?="",
    val publishedAt: String?="",
    //val title: String?="",
    val url: String ="",
    val urlToImage: String?="",
    var isChecked: Int = 0,
    var applied: Int = 0
) {


}

@Parcelize
data class Source(
    val id: String,
    val name: String
): Parcelable
