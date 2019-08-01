package com.example.taskviewer.domain.service

import com.example.taskviewer.domain.model.FeedDTO
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface TaskService {
    @GET("feed.json")
    fun getFeed(): Single<List<FeedDTO.Feed>>

    @GET("task/{id}.json")
    fun getTask(@Path("id") id: Int): Single<FeedDTO.Task>

    @GET("profile/{id}.json")
    fun getProfile(@Path("id") id: Int): Single<FeedDTO.Profile>

    companion object {
        fun create(): TaskService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.pexels.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(TaskService::class.java)
        }
    }
}