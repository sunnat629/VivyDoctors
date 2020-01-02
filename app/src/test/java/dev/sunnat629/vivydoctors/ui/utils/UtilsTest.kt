package dev.sunnat629.vivydoctors.ui.utils

import dev.sunnat629.vivydoctors.domain.doctors.DoctorsEntity
import dev.sunnat629.vivydoctors.ui.utils.DSConstants.RECENT_DOCTOR_LIST_SIZE
import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsTest {


    @Test
    fun reversedListTest() {

        val size = RECENT_DOCTOR_LIST_SIZE

        var actualList = listOf<Int>()
        assertEquals(listOf<Int>(), actualList)

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

    @Test
    fun searchedTest() {
        val actualList: List<DoctorsEntity> = listOf(
            DoctorsEntity(name = "sad"),
            DoctorsEntity(name = "ewr"),
            DoctorsEntity(name = "fde"),
            DoctorsEntity(name = "ytuj"),
            DoctorsEntity(name = "vxcv"),
            DoctorsEntity(name = "ertrt"),
            DoctorsEntity(name = "sedda")
        )
        assertEquals(2, searchDoctors(actualList, "s").size)

        assertEquals(4, searchDoctors(actualList, "E").size)

        assertEquals(4, searchDoctors(actualList, "e").size)

        assertEquals(1, searchDoctors(actualList, "sA").size)

        assertEquals(1, searchDoctors(actualList, "cv").size)
    }

    @Test
    fun zeroSearchedTest() {
        val actualList: List<DoctorsEntity> = listOf(
            DoctorsEntity(name = "sad"),
            DoctorsEntity(name = "ewr"),
            DoctorsEntity(name = "fde"),
            DoctorsEntity(name = "ytuj"),
            DoctorsEntity(name = "vxcv"),
            DoctorsEntity(name = "ertrt"),
            DoctorsEntity(name = "sedda")
        )

        assertEquals(0, searchDoctors(actualList, "Mohi").size)
    }
}