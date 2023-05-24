package com.example.todoapp.model

import kotlinx.serialization.Serializable

@Serializable
data class ToDo(
    val title: String,
    val description: String,
    val time: String,
    val date: String,
    val isDone: Boolean
)
