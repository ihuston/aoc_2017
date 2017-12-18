package net.ianhuston.adventofcode

import java.io.File
import kotlin.math.abs

fun main(args: Array<String>) {
    val inputText = File("/Users/ihuston/code/aoc_2017/data/day13.txt").readText()
    println("Severity = " + findSeverity(inputText))
    println("Min Delay = " + findMinDelay(inputText))
}

fun findSeverity(input: String, delay: Int = 0): Int {
    val levels = parseDay13Input(input)

    val caught = levels.mapValues { caught(it.key, it.value, delay) }

    return levels.map {
        if (caught[it.key] == true) it.key*it.value else 0
    }.sum()
}

private fun parseDay13Input(input: String): Map<Int, Int> {
    val levels = input.lines().filter { it != "" }
            .associate {
                Pair(it.split(": ").first().toInt(),
                        it.split(": ").last().toInt())
            }
    return levels
}

fun caught(level: Int, depth: Int, delay: Int): Boolean {
    if (depth < 1) return false
    if (depth == 1) return true

    val location = abs((((level+delay) + depth - 1) % (2 * (depth - 1))) - (depth - 1))
    return location == 0
}

fun findMinDelay(input: String): Int {
    val levels = parseDay13Input(input)
    var delay = -1
    do {
        delay++
        val isCaught = levels.map { caught(it.key, it.value, delay) }
    } while (true in isCaught)
    return delay
}

