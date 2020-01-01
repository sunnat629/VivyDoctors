package dev.sunnat629.vivydoctors.ui.utils

import dev.sunnat629.vivydoctors.ui.utils.DSConstants.RECENT_DOCTOR_LIST_SIZE
import org.junit.Assert.assertEquals
import org.junit.Test

class UiUtilsTest {


    @Test
    fun reversedListTest() {

        var actualList = listOf<Int>()
        val size = RECENT_DOCTOR_LIST_SIZE

        actualList = getRecentDoctors(actualList.toMutableList(), 4, size)
        assertEquals(listOf(4), actualList)

        actualList = getRecentDoctors(actualList.toMutableList(), 7, size)
        assertEquals(listOf(4, 7), actualList)

        actualList = getRecentDoctors(actualList.toMutableList(), 5, size)
        assertEquals(listOf(4, 7, 5), actualList)

        actualList = getRecentDoctors(actualList.toMutableList(), 7, size)
        assertEquals(listOf(4, 5, 7), actualList)

        actualList = getRecentDoctors(actualList.toMutableList(), 4, size)
        assertEquals(listOf(5, 7, 4), actualList)

        actualList = getRecentDoctors(actualList.toMutableList(), 9, size)
        assertEquals(listOf(7, 4, 9), actualList)
    }
}