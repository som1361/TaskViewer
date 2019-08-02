package com.example.taskviewer.model

import com.example.taskviewer.domain.Repository.TaskRepository
import com.example.taskviewer.domain.model.FeedItem
import com.example.taskviewer.domain.model.Profile
import com.example.taskviewer.domain.model.Task
import com.example.taskviewer.domain.service.TaskService
import io.reactivex.Observable

class NetworkTaskRepository : TaskRepository {
    override fun getTask(taskId: Int): Observable<Task> {
        return taskApiServe.getTask(taskId)
    }

    override fun getProfile(profileId: Int): Observable<Profile> {
        return taskApiServe.getProfile(profileId)
    }

    val taskApiServe by lazy {
        TaskService.create()
    }

    override fun getFeed(): Observable<List<FeedItem>> {
        return taskApiServe.getFeed()
    }

}

