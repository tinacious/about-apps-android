package com.tinaciousdesign.aboutapps.events

sealed class AppEvent {
    data object ConnectionLost : AppEvent()

    data object ConnectionRestored : AppEvent()
}
