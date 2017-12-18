package net.ianhuston.adventofcode

import java.io.File

fun main(args: Array<String>) {
    val inputText = File("/Users/ihuston/code/aoc_2017/data/day18.txt").readText()
    println(day18(inputText))
}

fun day18(inputText: String): Long? {
    val instructions = parseDay18Input(inputText)
    val registers = ('a'..'z').associate { Pair(it, 0L) }.toMutableMap()
    val pointer = 0
    val p = Program(registers, instructions, pointer)
    val retval = p.mainLoop()
    return retval
}


fun parseDay18Input(inputText: String): List<String> {
    return inputText.lines().filter { it != "" }
}

data class Program(var registers: MutableMap<Char, Long>,
                   var instructions: List<String>,
                   var pointer: Int) {

    fun mainLoop(): Long? {
        var rcvValue: Long? = null

        while ((0 <= pointer) and (pointer < instructions.size)) {
            val elements = instructions[pointer].split(" ")
            val first = elements[1]
            val second = if (elements.size >= 3) getValue(elements[2]) else 0
            println(elements)
            when(elements[0]) {
                "snd" -> snd(getValue(first))
                "set" -> set(first[0], second)
                "add" -> add(first[0], second)
                "mul" -> mul(first[0], second)
                "mod" -> mod(first[0], second)
                "rcv" -> {
                    rcvValue = rcv(getValue(first))
                    if (getValue(first) != 0L) {
                        println("first = ${getValue(first)}")
                        println("RCV value = $rcvValue")
                        return rcvValue
                    }
                }
                "jgz" -> jgz(getValue(first), second.toInt())
            }
            pointer++
        }
        return rcvValue
    }

    fun snd(r: Long): Program {
        registers['!'] = r
        return this
    }

    fun set(x: Char, n: Long): Program {
        registers[x] = n
        return this
    }

    fun add(x: Char, n: Long): Program {
        registers[x] = (registers[x] ?: 0) + n
        return this
    }

    fun mul(x: Char, n: Long): Program {
        registers[x] = (registers[x] ?: 0) * n
        return this
    }

    fun mod(x: Char, n: Long): Program{
        registers[x] = (registers[x] ?: 0) % n
        return this
    }

    fun rcv(n: Long): Long? {
        return if (n != 0L) registers['!'] else null
    }

    fun jgz(a: Long, inc: Int) {
        if (a > 0) pointer += (inc - 1)
    }

    fun getValue(x: Char): Long {
        return registers[x] ?: 0
    }

    fun getValue(n: Long): Long {
        return n
    }

    fun getValue(s: String): Long {
        return when {
            s[s.length-1].isDigit() -> getValue(s.toLong())
            s.length == 1 -> getValue(s[0])
            else -> throw Exception("Unknown value '$s'")
        }
    }

}