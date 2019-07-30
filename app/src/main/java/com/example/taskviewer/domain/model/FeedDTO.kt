package com.example.taskviewer.domain.model

object FeedDTO {
    data class Feed(val task_id: Int, val profile_id: Int, val text: String, val created_at: String, val event: String)
    data class Task(val id: Int, val name: String, val description: String)
    data class Profile(val id: Int, val avatar_mini_url: String, val first_name: String)
}