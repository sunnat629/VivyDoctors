package dev.sunnat629.vivydoctors

import dev.sunnat629.vivydoctors.data.utils.TIME_FORMAT_EEEE_HH_MM
import dev.sunnat629.vivydoctors.data.utils.timeFormatToPattern
import org.junit.Assert.assertEquals
import org.junit.Test

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
    fun dateFormatTest() {
        val time = "D1T08:00/D1T12:59"
        val expectedTime = "Monday, 08:00 to 12"

        time.timeFormatToPattern(TIME_FORMAT_EEEE_HH_MM)

    }
}
