package com.example.taskviewer.domain.service

import com.example.taskviewer.domain.model.FeedDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TaskService {
    @GET("feed.json")
    fun getFeed(): Single<List<FeedDTO.Feed>>

    @GET("task/{id}.json")
    fun getTask(@Path("id") id: Int): Single<FeedDTO.Task>

    @GET("profile/{id}.json")
    fun getProfile(@Path("id") id: Int): Single<FeedDTO.Profile>

}