package net.ianhuston.adventofcode

import org.junit.Assert.*
import org.junit.Test

class Day16KtTest {

    @Test
    fun testSpin() {
        assertEquals("eabcd", spin("abcde", 1))
        assertEquals("deabc", spin("abcde", 2))
        assertEquals("cdeab", spin("abcde", 3))
        assertEquals("cdeabx", spin("abxcde", 3))
    }

    @Test
    fun testExchange() {
        assertEquals("eabdc", exchange("eabcd", 3, 4))
        assertEquals("aebcd", exchange("eabcd", 0, 1))
        assertEquals("edbca", exchange("eabcd", 4, 1))
        assertEquals("edbcaf", exchange("eabcdf", 4, 1))
    }

    @Test
    fun testPartner() {
        assertEquals("baedc", partner("eabdc", 'e', 'b'))
        assertEquals("cbade", partner("abcde", 'a', 'c'))
        assertEquals("cbadef", partner("abcdef", 'a', 'c'))
    }

    @Test
    fun testDance() {
        val starting = sixteenPrograms()
        assertEquals("pabcdefghijklmno",
                dance("s1", starting))
        assertEquals("ghijklmnopabcdef",
                dance("s10", starting))
        assertEquals("akcdefghijblmnop",
                dance("x1/10", starting))
        assertEquals("abcpefghijklmnod",
                dance("pd/p", starting))
    }

}