package com.example.taskviewer.model

import com.example.taskviewer.domain.Repository.TaskRepository
import com.example.taskviewer.domain.model.FeedItemDTO
import com.example.taskviewer.domain.model.ProfileDTO
import com.example.taskviewer.domain.model.TaskDTO
import com.example.taskviewer.domain.service.TaskService
import io.reactivex.Observable

class NetworkTaskRepository : TaskRepository {
    override fun getTask(taskId: Int): Observable<TaskDTO> {
        return taskApiServe.getTask(taskId)
    }

    override fun getProfile(profileId: Int): Observable<ProfileDTO> {
        return taskApiServe.getProfile(profileId)
    }

    val taskApiServe by lazy {
        TaskService.create()
    }

    override fun getFeed(): Observable<List<FeedItemDTO>> {
        return taskApiServe.getFeed()
    }

}

