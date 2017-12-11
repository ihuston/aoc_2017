package net.ianhuston.adventofcode

import java.io.File
import java.lang.Math.abs

fun main(args: Array<String>) {
    val inputText = File("/Users/ihuston/code/aoc_2017/data/day11.txt").readText()
    println("(Last, Max)=" + findNumberOfSteps(inputText))
}

fun findNumberOfSteps(input: String): Pair<Int, Int> {
    val steps = parseInput(input)
    val stepsTaken = mutableListOf(Triple(0,0,0))

    steps.forEach {
        val a = stepsTaken.last()
        stepsTaken.add(Triple(a.first+ it.first,
                a.second+ it.second, a.third+ it.third))
    }
    val maxDistance = stepsTaken.map { hexDistance(it) }.max() ?: 0
    val lastDistance = hexDistance(stepsTaken.last())
    return Pair(lastDistance, maxDistance)
}

private fun hexDistance(finalHex: Triple<Int, Int, Int>) =
        (abs(finalHex.first) + abs(finalHex.second) + abs(finalHex.third)) / 2

fun parseInput(input:String): List<Triple<Int,Int,Int>> {
    return input.split(",").filter { it != "" }.map {
        when(it.trim()) {
            "ne" -> Triple(1,0,-1)
            "n" -> Triple(0,1,-1)
            "nw" -> Triple(-1,1,0)
            "sw" -> Triple(-1,0,1)
            "s" -> Triple(0,-1,1)
            "se" -> Triple(1,-1,0)
            else -> throw Exception("Unknown direction $it!")
        }
    }

}

