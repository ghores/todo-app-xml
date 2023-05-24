package com.example.todoapp.db

import androidx.datastore.core.Serializer
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class ToDoListSerializer : Serializer<ToDoListHolder> {
    override val defaultValue: ToDoListHolder = ToDoListHolder()

    override suspend fun readFrom(input: InputStream): ToDoListHolder {
        return Json.decodeFromString(
            ToDoListHolder.serializer(),
            input.readBytes().decodeToString()
        )
    }

    override suspend fun writeTo(t: ToDoListHolder, output: OutputStream) {
        output.write(Json.encodeToString(ToDoListHolder.serializer(), t).encodeToByteArray())
    }
}