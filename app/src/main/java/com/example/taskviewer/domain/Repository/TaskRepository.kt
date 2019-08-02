package com.example.taskviewer.domain.Repository

import com.example.taskviewer.domain.model.FeedItemDTO
import com.example.taskviewer.domain.model.ProfileDTO
import com.example.taskviewer.domain.model.TaskDTO
import io.reactivex.Observable

interface TaskRepository {
    fun getFeed(): Observable<List<FeedItemDTO>>
    fun getTask(taskId:Int): Observable<TaskDTO>
    fun getProfile(profileId:Int): Observable<ProfileDTO>

}