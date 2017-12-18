package net.ianhuston.adventofcode

import org.junit.Assert.*
import org.junit.Test

class Day4KtTest {

    @Test
    fun testNoDuplicateWords() {
        assertTrue(noDuplicateWords(listOf("aa", "bb", "cc", "dd")))
        assertFalse(noDuplicateWords(listOf("aa", "bb", "cc", "aa")))
        assertTrue(noDuplicateWords(listOf("aa", "bb", "cc", "aaa")))
    }

    @Test
    fun testNoAnagramWords() {
        assertTrue(noAnagramWords(listOf("abcde", "fghij")))
        assertFalse(noAnagramWords(listOf("abcde", "xyz", "ecdab")))
        assertTrue(noAnagramWords(listOf("a", "ab", "abc", "abf", "abj")))
        assertTrue(noAnagramWords(listOf("iiii", "oiii", "ooii", "oooi", "oooo")))
        assertFalse(noAnagramWords(listOf("oiii", "ioii", "iioi", "iiio")))
    }

    @Test
    fun countValid() {
        assertEquals(2,countValid("aa bb cc dd\n" +
                                      "abcde xyz ecdab\n" +
                                      "iiii oiii ooii oooi oooo"))
    }
}