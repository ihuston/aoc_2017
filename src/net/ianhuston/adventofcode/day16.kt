package net.ianhuston.adventofcode

import java.io.File

fun main(args: Array<String>) {
    val inputText = File("/Users/ihuston/code/aoc_2017/data/day16.txt").readText().trim()
    println("One dance: " + dance(inputText, sixteenPrograms()))
    println("One Billion dances: " + billionDances(inputText, sixteenPrograms()))
}

fun sixteenPrograms(): String {
    return ('a'..'p').joinToString("")
}

fun billionDances(moves: String, startingPositions: String): String {
    var positions = startingPositions
    var match = -1
    for (i in 1..1_000_000_000) {
        positions = dance(moves, positions)
        if(positions==startingPositions) {
            match = i
            break
        }
    }
    positions = startingPositions
    for (i in 1..1_000_000_000 % match) {
        positions = dance(moves, positions)
    }
    return positions
}

fun dance(moves: String, startingPositions: String): String {
    var positions = startingPositions
    moves.split(",").forEach {
        positions = when(it[0]) {
            's' -> spin(positions, it.substring(1).toInt())
            'x' -> exchange(positions, it.substringAfter('x').substringBefore('/').toInt(),
                    it.substringAfter('/').toInt())
            'p' -> partner(positions, it[1], it[3])
            else -> throw Exception("Unknown command \"$it\"")
        }
    }
    return positions
}

fun spin(input: String, numberToSpin: Int): String {
    return input.substring(input.length - numberToSpin) +
            input.substring(0, input.length - numberToSpin)
}

fun exchange(input: String, a: Int, b: Int): String {
    val temp = input.subSequence(a, a+1)
    return input.replaceRange(a, a+1, input.subSequence(b, b+1))
            .replaceRange(b, b+1, temp)
}

fun partner(input: String, a: Char, b: Char): String {
    return input.replace(a, 'x').replace(b, a).replace('x', b)
}