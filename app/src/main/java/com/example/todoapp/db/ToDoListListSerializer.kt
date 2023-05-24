package com.example.todoapp.db

import com.example.todoapp.model.ToDo
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class ToDoListListSerializer(val dataKSerializer: KSerializer<ToDo>) :
    KSerializer<PersistentList<ToDo>> {
    class PersistentListDescriptor : SerialDescriptor by serialDescriptor<List<ToDo>>()

    override val descriptor: SerialDescriptor = PersistentListDescriptor()

    override fun deserialize(decoder: Decoder): PersistentList<ToDo> {
        return ListSerializer(dataKSerializer).deserialize(decoder).toPersistentList()
    }

    override fun serialize(encoder: Encoder, value: PersistentList<ToDo>) {
        return ListSerializer(dataKSerializer).serialize(encoder, value.toList())
    }

}