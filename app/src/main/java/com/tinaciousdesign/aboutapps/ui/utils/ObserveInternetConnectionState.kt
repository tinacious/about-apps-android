package com.tinaciousdesign.aboutapps.ui.utils

import androidx.compose.runtime.Composable
import com.tinaciousdesign.aboutapps.R
import com.tinaciousdesign.aboutapps.events.AppEvent
import com.tinaciousdesign.aboutapps.events.EventBus
import com.tinaciousdesign.aboutapps.events.ObserveAsEvents
import com.tinaciousdesign.aboutapps.ui.snackbar.SnackBarController
import com.tinaciousdesign.aboutapps.ui.snackbar.SnackBarEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ObserveInternetConnectionState(
    eventBus: EventBus,
    coroutineScope: CoroutineScope,
) {
    ObserveAsEvents(eventBus.events) { appEvent ->
        coroutineScope.launch {
            when (appEvent) {
                AppEvent.ConnectionLost -> {
                    SnackBarController.sendEvent(
                        SnackBarEvent({ resources ->
                            resources.getString(R.string.connection_lost_message)
                        })
                    )
                }
                AppEvent.ConnectionRestored -> {
                    SnackBarController.sendEvent(
                        SnackBarEvent({ resources ->
                            resources.getString(R.string.connection_restored_message)
                        })
                    )
                }
                else -> {}
            }
        }
    }
}
