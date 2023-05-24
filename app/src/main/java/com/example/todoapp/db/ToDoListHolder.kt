package com.example.todoapp.db

import com.example.todoapp.model.ToDo
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable

@Serializable
data class ToDoListHolder(
    @Serializable(ToDoListListSerializer::class)
    val todoList: PersistentList<ToDo> = persistentListOf()
)