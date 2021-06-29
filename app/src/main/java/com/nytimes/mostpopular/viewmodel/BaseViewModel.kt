package com.nytimes.mostpopular.viewmodel

import androidx.lifecycle.ViewModel
import com.nytimes.mostpopular.utility.Event
import com.nytimes.mostpopular.utility.EventIdentifier
import com.nytimes.mostpopular.utility.EventType

open class BaseViewModel : ViewModel() {

    val onEventReceived: Event<EventType> = Event()

    fun triggerEvent(type: EventIdentifier) {
        onEventReceived(EventType(type))
    }
}