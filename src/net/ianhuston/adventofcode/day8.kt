package net.ianhuston.advent

import java.io.File

fun main(args: Array<String>) {
    val inputText = File("/Users/ihuston/code/advent/data/day8.txt").readText()
    val registers = runInstructions(inputText)
    println("Final max= ${registers.values.max()}")
}

fun runInstructions(inputText: String): Map<String, Int> {
    val lines = inputText.lines().filter { it.isNotBlank() }
    val registers = mutableMapOf<String, Int>()
    var runningMax = 0

    for (line in lines) {
        val condition = line.substringAfter("if ")
        val instruction = line.substringBefore(" if")
        val register = line.substringBefore(" ")
        if (checkConditional(condition, registers)) {
            val newValue = newValue(instruction, registers)
            registers.put(register, newValue)
            if (newValue > runningMax) runningMax = newValue
        }
    }
    println("Running Max=$runningMax")
    return registers
}

fun checkConditional(condition: String, registers: Map<String, Int>): Boolean {
    val (register, op, valueString) = condition.split(" ")
    val value = valueString.toInt()
    return when(op) {
        ">" -> registers.getOrDefault(register, 0) > value
        "==" -> registers.getOrDefault(register, 0) == value
        "<" -> registers.getOrDefault(register, 0) < value
        "<=" -> registers.getOrDefault(register, 0) <= value
        ">=" -> registers.getOrDefault(register, 0) >= value
        "!=" -> registers.getOrDefault(register, 0) != value
        else -> throw Exception("Unknown Operation $op")
    }
}

fun newValue(instruction: String, registers: Map<String, Int>): Int {
    val (register, op, valueString) = instruction.split(" ")
    val value = valueString.toInt()
    return when(op) {
        "inc" -> registers.getOrDefault(register, 0) + value
        "dec" -> registers.getOrDefault(register, 0) - value
        else -> throw Exception("Unknown Operation $op")
    }
}