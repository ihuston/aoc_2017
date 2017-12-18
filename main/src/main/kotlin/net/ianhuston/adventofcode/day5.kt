package net.ianhuston.adventofcode

import java.io.File

fun main(args: Array<String>) {
    val inputText = File("/Users/ihuston/code/advent/data/day5.txt").readLines()
    val inputArray = (inputText.map { it.toInt() }).toMutableList()
    val program = Day5Program(inputArray, 0)
    println(exitCount(program))
}

data class Day5Program(val offsetList: MutableList<Int>, val index: Int)

fun takeStep(program: Day5Program): Day5Program {
    val offsets = program.offsetList
    val index = program.index
    val newIndex = index + offsets[index]
    if (offsets[index] >= 3) {
        offsets[index]--
    } else {
        offsets[index]++
    }

    return Day5Program(offsets, newIndex)
}

fun exitedList(program: Day5Program): Boolean {
    return (program.index < 0) or (program.index >= program.offsetList.size)
}

fun exitCount(startProgram: Day5Program): Int {
    var steps = 0
    var program = startProgram
    while (!exitedList(program)) {
        program = takeStep(program)
        steps++
    }
    return steps
}