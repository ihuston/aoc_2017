package net.ianhuston.adventofcode

import org.junit.Assert.*
import org.junit.Test

class Day8KtTest {

    @Test
    fun testDecodeInstruction() {

    }

    @Test
    fun testCheckConditional() {
        val registers = mapOf("a" to 0, "b" to 0)
        assertTrue(checkConditional("a > -1", registers))
        assertTrue(checkConditional("a < 10", registers))
        assertTrue(checkConditional("a == 0", registers))
        assertTrue(checkConditional("a <= 0", registers))
        assertTrue(checkConditional("a <= 1", registers))
        assertTrue(checkConditional("a >= 0", registers))
        assertTrue(checkConditional("a >= -1", registers))
        assertTrue(checkConditional("a != -1", registers))
    }

    @Test
    fun testNewValue() {
        val registers = mapOf("a" to 0, "b" to 5)
        assertEquals(10, newValue("a inc 10", registers))
        assertEquals(-10, newValue("a inc -10", registers))
        assertEquals(0, newValue("b dec 5", registers))
        assertEquals(10, newValue("b dec -5", registers))
    }

    @Test
    fun testRunInstructions() {
        val inputText = "b inc 5 if a > 1\n" +
                "a inc 1 if b < 5\n" +
                "c dec -10 if a >= 1\n" +
                "c inc -20 if c == 10"
        val expected = mapOf("a" to 1, "c" to -10)
        assertEquals(expected, runInstructions(inputText))
    }
}