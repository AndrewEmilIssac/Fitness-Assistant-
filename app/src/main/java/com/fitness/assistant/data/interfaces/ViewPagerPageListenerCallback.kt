

package com.fitness.assistant.data.interfaces


interface ViewPagerPageListenerCallback {
    fun updateDayAndDate(position: Int)
    var scrollState: Int
    var isResettingDateOrSwiping: Boolean
}
