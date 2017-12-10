package net.ianhuston.adventofcode

import org.junit.Assert.*
import org.junit.Test

class Day2KtTest {

    @Test
    fun testSpreadsheet(){
        val testSheet = "5\t9\t2\t8\n" +
                        "9\t4\t7\t3\n" +
                        "3\t8\t6\t5"

        assertEquals(9, checkSpreadsheet(testSheet))
    }



    @Test
    fun testRow(){
        assertEquals(4, rowDivision("5\t9\t2\t8"))
        assertEquals(3, rowDivision("9\t4\t7\t3"))
        assertEquals(2, rowDivision("3\t8\t6\t5"))
    }
}