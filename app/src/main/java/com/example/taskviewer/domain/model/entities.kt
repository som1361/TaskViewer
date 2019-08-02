package com.example.taskviewer.domain.model

data class FeedItem(val task_id: Int, val profile_id: Int, var text: String, var created_at: String, var event: String, var image_url: String?)
data class Task(val id: Int, val name: String, val description: String)
data class Profile(val id: Int, val avatar_mini_url: String, val first_name: String)