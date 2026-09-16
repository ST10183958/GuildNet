package com.codehijackers.guldnet.viewmodel

import androidx.lifecycle.ViewModel
import com.codehijackers.guldnet.model.Message
import com.codehijackers.guldnet.repository.MessageRepository
import kotlinx.coroutines.flow.StateFlow

class MessageViewModel : ViewModel() {

    private val repository = MessageRepository

    val messages: StateFlow<List<Message>> =
        repository.messages

    fun getMessagesForSquad(squadId: String): List<Message> {
        return repository.getMessagesForSquad(squadId)
    }

    fun sendMessage(
        squadId: String,
        content: String,
        senderName: String
    ) {
        repository.sendMessage(
            squadId = squadId,
            senderName = senderName,
            content = content
        )
    }
}