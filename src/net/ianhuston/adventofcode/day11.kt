package net.ianhuston.adventofcode

import java.io.File
import java.lang.Math.abs

var maxSteps = 0

fun main(args: Array<String>) {
    val inputText = File("/Users/ihuston/code/aoc_2017/data/day11.txt").readText()
    println(findNumberOfSteps(inputText))
    println(maxSteps)
}

fun findNumberOfSteps(input: String): Int {
    val steps = parseInput(input)
    val finalHex = steps.reduce { a,b -> takeStep(a, b)}
    return hexDistance(finalHex)
}

private fun hexDistance(finalHex: Triple<Int, Int, Int>) =
        (abs(finalHex.first) + abs(finalHex.second) + abs(finalHex.third)) / 2

fun takeStep(a: Triple<Int,Int,Int>, b: Triple<Int, Int, Int>): Triple<Int,Int,Int> {

    val next = Triple(a.first+b.first,
            a.second+b.second, a.third+b.third)
    maxSteps = maxOf(maxSteps, hexDistance(next))
    return next
}

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

