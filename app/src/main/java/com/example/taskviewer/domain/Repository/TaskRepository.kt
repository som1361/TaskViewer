package com.example.taskviewer.domain.Repository

import com.example.taskviewer.domain.model.FeedItem
import com.example.taskviewer.domain.model.Profile
import com.example.taskviewer.domain.model.Task
import io.reactivex.Observable

interface TaskRepository {
    fun getFeed(): Observable<List<FeedItem>>
    fun getTask(taskId:Int): Observable<Task>
    fun getProfile(profileId:Int): Observable<Profile>

}