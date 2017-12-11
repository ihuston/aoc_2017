package net.ianhuston.advent

import java.io.File

fun main(args: Array<String>) {
    val inputText = File("/Users/ihuston/code/advent/data/day5.txt").readLines()
    val inputArray = (inputText.map { it.toInt() }).toMutableList()
    val program = Program(inputArray, 0)
    println(exitCount(program))
}

data class Program(val offsetList: MutableList<Int>, val index: Int)

fun takeStep(program: Program): Program {
    val offsets = program.offsetList
    val index = program.index
    val newIndex = index + offsets[index]
    if (offsets[index] >= 3) {
        offsets[index]--
    } else {
        offsets[index]++
    }

    return Program(offsets, newIndex)
}

fun exitedList(program: Program): Boolean {
    return (program.index < 0) or (program.index >= program.offsetList.size)
}

fun exitCount(startProgram: Program): Int {
    var steps = 0
    var program = startProgram
    while (!exitedList(program)) {
        program = takeStep(program)
        steps++
    }
    return steps
}