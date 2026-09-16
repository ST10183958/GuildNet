package com.codehijackers.guldnet.repository

import com.codehijackers.guldnet.model.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object MessageRepository {

    private val _messages = MutableStateFlow(
        listOf(
            Message(
                id = "1",
                squadId = "1",
                senderName = "Alex",
                content = "Hey everyone! Anyone want to work on the castle tonight?",
                createdAt = "18:42"
            ),
            Message(
                id = "2",
                squadId = "1",
                senderName = "Jordan",
                content = "I'm in! I can work on the walls.",
                createdAt = "18:44"
            ),
            Message(
                id = "3",
                squadId = "1",
                senderName = "Sam",
                content = "I'll gather some stone and materials.",
                createdAt = "18:45"
            ),

            Message(
                id = "4",
                squadId = "2",
                senderName = "Jordan",
                content = "Anyone up for some co-op?",
                createdAt = "19:10"
            ),
            Message(
                id = "5",
                squadId = "2",
                senderName = "Alex",
                content = "Sure! Give me a few minutes.",
                createdAt = "19:11"
            )
        )
    )

    val messages: StateFlow<List<Message>> =
        _messages.asStateFlow()

    fun getMessagesForSquad(squadId: String): List<Message> {
        return _messages.value.filter {
            it.squadId == squadId
        }
    }

    fun sendMessage(
        squadId: String,
        senderName: String,
        content: String
    ) {
        val message = Message(
            id = (_messages.value.size + 1).toString(),
            squadId = squadId,
            senderName = senderName,
            content = content,
            createdAt = "Just now"
        )

        _messages.value = _messages.value + message
    }
}