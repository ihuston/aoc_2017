package net.ianhuston.adventofcode

import org.junit.Assert.*
import org.junit.Test

class Day14KtTest {

    @Test
    fun testSquaresUsed() {
        assertEquals(8108, squaresUsed("flqrgnkx"))
    }

    @Test
    fun testHexToBinary() {
        assertEquals(0b1010000011000010000000010111.toBigInteger(),
                "a0c2017".toBigInteger(16))
    }

    @Test
    fun testCountBits() {
        assertEquals(9,
                0b1010000011000010000000010111.toBigInteger().bitCount())
    }

    @Test
    fun testCountRegions() {
        assertEquals(1242, countRegions("flqrgnkx"))
    }

    @Test
    fun testCountInSquares() {
        val input = listOf("11010100",
                           "01010101",
                           "00001010",
                           "10101101",
                           "01101000",
                           "11001000",
                           "01000100",
                           "11111110")
        assertEquals(9, countInSquares(input))
    }

    @Test
    fun testCountInSquaresUpsideDown() {
        val input = listOf("11010100",
                "01010101",
                "00001010",
                "10101101",
                "01101000",
                "11001000",
                "01000100",
                "11111110").reversed()
        assertEquals(9, countInSquares(input))
    }

    @Test
    fun testCountInSquaresWithCycles() {
        val input = listOf(
                "11111111",
                "10000001",
                "10000001",
                "10000001",
                "10000001",
                "11111111",
                "10000001",
                "10000001"
        )
        assertEquals(1, countInSquares(input))
    }

    @Test
    fun testAddConnections() {
        assertEquals(mapOf(0 to listOf(3), 3 to listOf(0), 5 to listOf()),
                addConnections("101", 1, mapOf(0 to mutableListOf())))
        assertEquals(mapOf(0 to listOf(3), 3 to listOf(0, 4), 4 to listOf(3, 5), 5 to listOf(4)),
                addConnections("111", 1, mapOf(0 to mutableListOf())))
        assertEquals(mapOf(0 to listOf(3), 2 to mutableListOf(), 3 to listOf(0)),
                addConnections("100", 1, mapOf(0 to mutableListOf(), 2 to mutableListOf())))
    }
}