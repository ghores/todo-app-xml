package com.example.todoapp.model

data class ToDo(
    val title: String,
    val description: String,
    val time: String,
    val date: String,
    val isDone: Boolean
)
