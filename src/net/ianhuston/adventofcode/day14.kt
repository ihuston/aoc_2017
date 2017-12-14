package net.ianhuston.adventofcode

import java.math.BigInteger

fun main(args: Array<String>) {
    println("Squares Used: " + squaresUsed("hfdlxzhv"))
    println("Regions: " + countRegions("hfdlxzhv"))

}

fun squaresUsed(input: String): Int {
    val numBits = prepareHashes(input).map { it.bitCount() }
    return numBits.sum()
}

private fun prepareHashes(input: String): List<BigInteger> {
    return (0..127).map { knotHash(input + "-" + it).toBigInteger(16) }
}

fun countRegions(input: String): Int {
    val squares = prepareHashes(input).map { it.toString(2).padStart(128, '0') }
    return countInSquares(squares)
}

fun countInSquares(squares: List<String>): Int {
    val connections = mutableMapOf<Int, MutableList<Int>>()
    for ((index, row) in squares.withIndex()) {
        connections.putAll(addConnections(row, index, connections))
    }
    return countGroups(connections)
}

fun addConnections(row: String, rowIndex: Int, connections: Map<Int, MutableList<Int>>): Map<out Int, MutableList<Int>> {
    val updatedConnections = connections.toMutableMap()
    for ((index, char) in row.withIndex()) {
        if (char == '1') {
            val here = (rowIndex*row.length) + index
            updatedConnections[here] = mutableListOf()
            val above = here - row.length
            if (above in updatedConnections.keys) {
                updatedConnections[above]?.add(here)
                updatedConnections[here]?.add(above)
            }
            if (index > 0) {
                val previous = here - 1
                if (previous in updatedConnections.keys) {
                    updatedConnections[previous]?.add(here)
                    updatedConnections[here]?.add(previous)
                }
            }
        }
    }
    return updatedConnections
}


