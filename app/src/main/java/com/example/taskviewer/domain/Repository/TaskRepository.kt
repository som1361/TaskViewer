package com.example.taskviewer.domain.Repository

import com.example.taskviewer.domain.model.FeedDTO
import io.reactivex.Single

interface TaskRepository {
    fun fetchFeed(): Single<List<FeedDTO.Feed>>
}