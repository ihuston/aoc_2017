package net.ianhuston.adventofcode

import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.SendChannel
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import java.io.File

fun main(args: Array<String>) = runBlocking {
    val inputText = File("/Users/ihuston/code/aoc_2017/data/day18.txt").readText()
    val mainjob = launch { day18(inputText) }
    mainjob.join()
}

suspend fun day18(inputText: String): Long? {
    val instructions = parseDay18Input(inputText)
    val p0 = Day18Program(instructions = instructions)
    p0.registers['p'] = 0
    p0.registers['#'] = 0
    val p1 = Day18Program(instructions = instructions)
    p1.registers['p'] = 1
    p1.registers['#'] = 1
    println("Starting jobs")
    val toP0Channel = Channel<Long>(1000)
    val toP1Channel = Channel<Long>(1000)
    val retval0 = launch {
        println("Job 0")
        println("P0 sent " + p0.mainLoop(toP0Channel, toP1Channel) + " times.")

    }
    val retval1 = launch {
        p1.mainLoop(toP1Channel, toP0Channel)
    }

    retval0.join()
    retval1.join()


    return 0L

}


fun parseDay18Input(inputText: String): List<String> {
    return inputText.lines().filter { it != "" }
}

data class Day18Program(var registers: MutableMap<Char, Long> = ('a'..'z').associate { Pair(it, 0L) }.toMutableMap(),
                        var instructions: List<String> = emptyList(),
                        var pointer: Int = 0) {

    suspend fun mainLoop(inChannel: ReceiveChannel<Long>,
                 outChannel: SendChannel<Long>): Int {
        var count = 0

        while ((0 <= pointer) and (pointer < instructions.size)) {
            val elements = instructions[pointer].split(" ")
            val first = elements[1]
            val second = if (elements.size >= 3) getValue(elements[2]) else 0
            println("${registers['#']}: " + elements)
            when(elements[0]) {
                "snd" -> {
                    snd(first[0], outChannel)
                    count++
                    println("${registers['#']} has sent " + count + " times.")
                }
                "set" -> set(first[0], second)
                "add" -> add(first[0], second)
                "mul" -> mul(first[0], second)
                "mod" -> mod(first[0], second)
                "rcv" -> rcv(first[0], inChannel)
                "jgz" -> jgz(getValue(first), second.toInt())
            }
            pointer++
        }
        return count
    }

    suspend fun snd(x: Char, channel: SendChannel<Long>): Day18Program {
        channel.send(registers[x] ?: 0)
        return this
    }

    fun set(x: Char, n: Long): Day18Program {
        registers[x] = n
        return this
    }

    fun add(x: Char, n: Long): Day18Program {
        registers[x] = (registers[x] ?: 0) + n
        return this
    }

    fun mul(x: Char, n: Long): Day18Program {
        registers[x] = (registers[x] ?: 0) * n
        return this
    }

    fun mod(x: Char, n: Long): Day18Program {
        registers[x] = (registers[x] ?: 0) % n
        return this
    }

    suspend fun rcv(x: Char, channel: ReceiveChannel<Long>) {
        println("${registers['#']}: Waiting to receive")
        registers[x] = channel.receive()
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