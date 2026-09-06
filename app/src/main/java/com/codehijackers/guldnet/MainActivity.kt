package com.codehijackers.guldnet

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehijackers.guldnet.ui.GuildnetApp
import com.codehijackers.guldnet.ui.theme.GuildnetTheme
import com.codehijackers.guldnet.viewmodel.AppViewModel

class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "Guildnet.MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "MainActivity onCreate")

        setContent {

            val appViewModel: AppViewModel = viewModel()

            GuildnetTheme {
                GuildnetApp(
                    viewModel = appViewModel
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()

        Log.d(TAG, "MainActivity onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.d(TAG, "MainActivity onResume")
    }

    override fun onPause() {
        Log.d(TAG, "MainActivity onPause")

        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "MainActivity onStop")

        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "MainActivity onDestroy")

        super.onDestroy()
    }
}