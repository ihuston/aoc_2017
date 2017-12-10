package net.ianhuston.adventofcode

import java.io.File

fun main(args: Array<String>) {
    val inputText = File("/Users/ian/code/advent_of_code/data/day9.txt").readText()
    val (cleanGroups, garbageCount) = discardUntilGarbageEnd(inputText)
    println("Number of groups=${groupCounter((cleanGroups))}")
    println("Garbage Count=$garbageCount")
}

fun discardUntilGarbageEnd(input: String): Pair<String, Int> {
    var output = ""
    var insideGarbage = false
    var garbageCounter = 0
    var i = 0
    while (i < input.length) {
        when(input[i]) {
            '<' -> if(insideGarbage) {garbageCounter++} else { insideGarbage = true }
            '>' -> insideGarbage = false
            '!' -> i++
            else -> if (insideGarbage) {garbageCounter++} else {output += input[i]}
        }
        i++
    }
    return Pair(output, garbageCounter)
}

fun groupCounter(input: String): Int {
    var total = 0
    var level = 0
    input.forEach {
        when(it) {
            '{' -> {
                level++
                total += level
            }
            '}' -> {
                level--
            }
            else -> {}
        }
    }
    return total
}