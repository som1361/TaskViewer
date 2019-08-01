package com.example.taskviewer.model

import com.example.taskviewer.domain.Repository.TaskRepository
import com.example.taskviewer.domain.model.FeedDTO
import com.example.taskviewer.domain.service.TaskService
import io.reactivex.Single

class NetworkTaskRepository: TaskRepository {
    val taskApiServe by lazy {
        TaskService.create()
    }

    override fun fetchFeed(): Single<List<FeedDTO.Feed>> {
        return taskApiServe.getFeed()
    }
}

