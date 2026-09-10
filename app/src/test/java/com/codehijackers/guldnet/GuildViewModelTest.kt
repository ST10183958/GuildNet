package com.codehijackers.guldnet

import com.codehijackers.guldnet.viewmodel.GuildViewModel
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class GuildViewModelTest {

    @Test
    fun guilds_areLoadedInitially() {
        val viewModel = GuildViewModel()

        assertTrue(viewModel.guilds.value.isNotEmpty())
    }

    @Test
    fun joinGuild_changesMembershipStatus() {
        val viewModel = GuildViewModel()

        viewModel.joinGuild("2")

        val guild = viewModel.guilds.value
            .find { it.id == "2" }

        assertTrue(guild?.isJoined == true)
    }

    @Test
    fun leaveGuild_changesMembershipStatus() {
        val viewModel = GuildViewModel()

        viewModel.joinGuild("2")
        viewModel.leaveGuild("2")

        val guild = viewModel.guilds.value
            .find { it.id == "2" }

        assertFalse(guild?.isJoined == true)
    }
}