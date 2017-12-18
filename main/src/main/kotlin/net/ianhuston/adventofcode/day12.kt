package net.ianhuston.adventofcode

import java.io.File

fun main(args: Array<String>) {
    val inputText = File("/Users/ihuston/code/aoc_2017/data/day12.txt").readText()
    val connections = parseDay12Input(inputText)
    println("Group 0 connections: " + countConnectionsSeen(connections, 0))
    println("Number of groups: " + countGroups(connections))
}

fun parseDay12Input(inputString: String): Map<Int, List<Int>> {
    val inputLines = inputString.lines().filter { it != "" }
    return inputLines.associate { parseSingleLine(it)  }
}

fun parseSingleLine(s: String): Pair<Int, List<Int>> {
    return Pair(s.substringBefore(" <->").toInt(),
            s.substringAfter("<-> ").split(", ").map { it.toInt() })
}

fun followConnections(connections: Map<Int, List<Int>>, start: Int): List<Int> {
    val seen = mutableListOf(0)
    val toFollow = (connections[start] ?: emptyList()).toMutableList()
    while (!toFollow.isEmpty()) {
        val current = toFollow.removeAt(0)
        val newConnections = connections[current] ?: emptyList()
        toFollow.addAll(newConnections.filter { it != current }.filter { it !in seen }.filter { it !in toFollow })
        seen.add(current)
    }
    return seen
}

fun countConnectionsSeen(connections: Map<Int, List<Int>>, start: Int): Int {
    return followConnections(connections, start).size
}

fun countGroups(connections: Map<Int, List<Int>>): Int {
    val counted = mutableListOf<Int>()
    var numberOfGroups = 0

    for (start in connections.keys) {
        if (start !in counted) {
            val newlySeen = followConnections(connections, start)
            counted.addAll(newlySeen.filter { it !in counted })
            numberOfGroups++
        }
    }
    return numberOfGroups

}