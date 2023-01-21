package com.kozak.pw

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import org.junit.Test

import org.junit.Assert.*
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test() {
        Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .also(::println)
            .toJavaLocalDateTime()
            .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT))
            .let(::println)
    }
}