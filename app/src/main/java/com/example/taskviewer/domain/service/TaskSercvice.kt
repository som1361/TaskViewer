package com.example.taskviewer.domain.service

import com.example.taskviewer.domain.model.*
import com.example.taskviewer.utils.BASE_URL
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface TaskService {
    @GET("feed.json")
    fun getFeed(): Observable<List<FeedItem>>

    @GET("task/{id}.json")
    fun getTask(@Path("id") id: Int): Observable<Task>

    @GET("profile/{id}.json")
    fun getProfile(@Path("id") id: Int): Observable<Profile>

    companion object {
        fun create(): TaskService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(TaskService::class.java)
        }
    }
}