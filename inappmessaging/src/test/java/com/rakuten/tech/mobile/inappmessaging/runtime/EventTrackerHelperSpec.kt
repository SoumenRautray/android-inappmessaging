package com.rakuten.tech.mobile.inappmessaging.runtime

import org.amshove.kluent.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner

/**
 * Tests for EventTrackerHelper class.
 */

@RunWith(ParameterizedRobolectricTestRunner::class)
class SendEventSpec(
    private val eventName: String,
    private val data: Map<String, *>?,
    private val expected: Boolean,
) {
    private val sendEvent = EventTrackerHelper::sendEvent

    @Test
    fun `should return if event was sent`() {
        sendEvent(eventName, data) shouldBeEqualTo expected
    }

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun data(): List<Array<out Any?>> {
            return listOf(
                arrayOf("event1", mapOf(Pair("param1", Any())), true),
                arrayOf("event2", emptyMap<String, Any>(), true),
                arrayOf("event3", mapOf(Pair(Any(), Any())), true),
                arrayOf("event4", null, true),
                arrayOf("", emptyMap<String, Any>(), false),
            )
        }
    }
}

@RunWith(ParameterizedRobolectricTestRunner::class)
class HasClassSpec(
    private val className: String,
    private val expected: Boolean,
) {
    private val hasClass = EventTrackerHelper::hasClass

    @Test
    fun `should return if class exists`() {
        hasClass(className) shouldBeEqualTo expected
    }

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun data(): List<Array<out Any?>> {
            return listOf(
                arrayOf("com.rakuten.tech.mobile.analytics.Event", true),
                arrayOf("", false),
                arrayOf("com.rakuten.tech.mobile.NonExistingClass", false),
            )
        }
    }
}
