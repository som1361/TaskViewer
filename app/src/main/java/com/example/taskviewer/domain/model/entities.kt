package com.example.taskviewer.domain.model

data class FeedItemDTO(val task_id: Int, val profile_id: Int, var text: String, var created_at: String, var event: String, var image_url: String?)
data class TaskDTO(val id: Int, val name: String, val description: String)
data class ProfileDTO(val id: Int, val avatar_mini_url: String, val first_name: String)
data class Task(val image_url: String, val text: String, val created_at:String, val event: String )