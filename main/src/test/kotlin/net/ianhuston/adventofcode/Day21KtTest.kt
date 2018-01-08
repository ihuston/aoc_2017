package net.ianhuston.adventofcode

import org.junit.Assert.*
import org.junit.Test

class Day21KtTest {

    @Test
    fun testParseDay21Input() {
        val input = "../.# => ##./#../...\n" +
                ".#./..#/### => #..#/..../..../#..#"

        val expected = mapOf(
                listOf("..", ".#") to listOf("##.", "#..", "..."),
                listOf("..", "#.") to listOf("##.", "#..", "..."),
                listOf("#.", "..") to listOf("##.", "#..", "..."),
                listOf(".#", "..") to listOf("##.", "#..", "..."),

                listOf(".#.", "..#", "###") to listOf("#..#", "....", "....", "#..#"),
                listOf(".#.", "#..", "###") to listOf("#..#", "....", "....", "#..#"),
                listOf("#..", "#.#", "##.") to listOf("#..#", "....", "....", "#..#"),
                listOf("..#", "#.#", ".##") to listOf("#..#", "....", "....", "#..#"),
                listOf("###", "..#", ".#.") to listOf("#..#", "....", "....", "#..#"),
                listOf("###", "#..", ".#.") to listOf("#..#", "....", "....", "#..#"),
                listOf(".##", "#.#", "..#") to listOf("#..#", "....", "....", "#..#"),
                listOf("##.", "#.#", "#..") to listOf("#..#", "....", "....", "#..#")
        )
        println(expected)
        println(parseDay21Input(input))
        assertEquals(expected, parseDay21Input(input))
    }

    @Test
    fun testParseGrid() {
        assertEquals(listOf("..", "#."), parseGrid("../#."))
        assertEquals(listOf("...", ".#.", "..#"), parseGrid(".../.#./..#"))
    }

    @Test
    fun testRotate90() {
        assertEquals(listOf("..", "#."), rotate90(listOf("..", ".#")))
        assertEquals(listOf("#.", ".."), rotate90(listOf("..", "#.")))
        assertEquals(listOf(".#", ".."), rotate90(listOf("#.", "..")))

        assertEquals(listOf("..#", "...", "..."), rotate90(listOf("#..", "...", "...")))
    }

    @Test
    fun testFlip() {
        assertEquals(listOf("..", "#."), flip(listOf("..", ".#")))
        assertEquals(listOf("...", "#..", "..."), flip(listOf("...", "..#", "...")))
    }

    @Test
    fun testPixelsStillOn() {
        val transformations = mapOf(
                listOf("..", ".#") to listOf("##.", "#..", "..."),
                listOf("..", "#.") to listOf("##.", "#..", "..."),
                listOf("#.", "..") to listOf("##.", "#..", "..."),
                listOf(".#", "..") to listOf("##.", "#..", "..."),

                listOf(".#.", "..#", "###") to listOf("#..#", "....", "....", "#..#"),
                listOf(".#.", "#..", "###") to listOf("#..#", "....", "....", "#..#"),
                listOf("#..", "#.#", "##.") to listOf("#..#", "....", "....", "#..#"),
                listOf("..#", "#.#", ".##") to listOf("#..#", "....", "....", "#..#"),
                listOf("###", "..#", ".#.") to listOf("#..#", "....", "....", "#..#"),
                listOf("###", "#..", ".#.") to listOf("#..#", "....", "....", "#..#"),
                listOf(".##", "#.#", "..#") to listOf("#..#", "....", "....", "#..#"),
                listOf("##.", "#.#", "#..") to listOf("#..#", "....", "....", "#..#")
        )
        val grid = listOf(".#.", "..#", "###")
        assertEquals(12, pixelsStillOn(grid, transformations, iterations=2))
    }

    @Test
    fun testBreakUpSize2() {
        val input = listOf("#..#", "....", "....", "#..#")

        val expected = listOf(
                listOf("#.", ".."),
                listOf(".#", ".."),
                listOf("..", "#."),
                listOf("..", ".#")
        )

        assertEquals(expected, breakUp(input))
    }

    @Test
    fun testBreakUpSize3() {
        val input = listOf(
                "#...#...#",
                ".........",
                ".........",
                ".........",
                ".........",
                ".........",
                ".........",
                ".........",
                "#...#...#")

        val expected = listOf(
                listOf("#..", "...", "..."),
                listOf(".#.", "...", "..."),
                listOf("..#", "...", "..."),
                listOf("...", "...", "..."),
                listOf("...", "...", "..."),
                listOf("...", "...", "..."),
                listOf("...", "...", "#.."),
                listOf("...", "...", ".#."),
                listOf("...", "...", "..#")
        )

        assertEquals(expected, breakUp(input))
    }

    @Test
    fun testTransformBlocks() {
        val input = listOf("#..#", "....", "....", "#..#")
        val transformations = mapOf(
                listOf("..", ".#") to listOf("##.", "#..", "..."),
                listOf("..", "#.") to listOf("##.", "#..", "..."),
                listOf("#.", "..") to listOf("##.", "#..", "..."),
                listOf(".#", "..") to listOf("##.", "#..", "...")
        )
        val expected =
                listOf("##.##.", "#..#..", "......", "##.##.", "#..#..", "......")

        assertEquals(expected, transformBlocks(input, transformations))
    }

}