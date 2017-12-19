package net.ianhuston.adventofcode

import java.io.File

fun main(args: Array<String>) {
    val input = File("/Users/ihuston/code/aoc_2017/data/day19.txt").readText()
    println("Letters: " + followPathFindingLetters(parseDay19Input(input)))
    println("Steps: " + followPathCounting(parseDay19Input(input)))
}

fun parseDay19Input(input: String): List<List<Char>> {
    return input.lines().filter { it != "" }.map { it.toList() }
}

fun followPathFindingLetters(grid: List<List<Char>>): String {
    val traveller = followPath(grid)
    return traveller.lettersSeen.joinToString("")
}

fun followPathCounting(grid: List<List<Char>>): Int {
    val traveller = followPath(grid)
    return traveller.stepsTaken
}

private fun followPath(grid: List<List<Char>>): Traveller {
    val traveller = Traveller(startingPoint(grid),
            Pair(0, 1),
            mutableListOf(),
            grid)
    while (!traveller.endOfPath()) {
        traveller.takeStep()
    }
    return traveller
}

fun startingPoint(grid: List<List<Char>>): Pair<Int, Int> {
    return Pair(grid[0].indexOf('|'), 0)
}

data class Traveller(var location: Pair<Int, Int>,
                     var direction: Pair<Int, Int>,
                     val lettersSeen: MutableList<Char>,
                     val grid: List<List<Char>>,
                     var stepsTaken: Int = 0) {

    fun takeStep(): Traveller {
        val currentSquare = currentSquare()
        println("Parsing: $currentSquare")
        when {
            (currentSquare == '+') -> {
                changeDirection()
                goForward()
            }
            (currentSquare in listOf('|', '-')) -> goForward()
            (currentSquare.isLetter()) -> {
                println("Letter Found: $currentSquare")
                collectLetter(currentSquare)
                goForward()
            }
            else -> throw Exception("No Path here: '$currentSquare'")
        }
        return this
    }

    fun changeDirection() {
        if (squareOnLeft() != ' ') direction = onLeft()
        else if (squareOnRight() != ' ') direction = onRight()
    }

    fun goForward() {
        location = Pair(location.first + direction.first,
                        location.second + direction.second)
        stepsTaken++
    }

    fun collectLetter(l: Char) = lettersSeen.add(l)

    fun currentSquare() = getSquare(location.first, location.second)

    fun endOfPath(): Boolean = (currentSquare() == ' ')

    fun getSquare(x: Int, y: Int): Char {
        return if ((x < 0) or (y < 0) or (x >= grid[0].size) or (y >= (grid.size))) ' '
        else grid[y][x]
    }

    fun onLeft(): Pair<Int, Int> {
        return when(direction) {
            Pair(1, 0) -> Pair(0, 1)
            Pair(0, 1) -> Pair(-1, 0)
            Pair(-1, 0) -> Pair(0, -1)
            Pair(0, -1) -> Pair(1, 0)
            else -> throw Exception("Unknown direction '$direction'.")
        }
    }

    fun squareOnLeft(): Char {
        return getSquare(location.first + onLeft().first,
                    location.second + onLeft().second)
    }

    fun onRight(): Pair<Int, Int> {
        return when(direction) {
            Pair(1, 0) -> Pair(0, -1)
            Pair(0, 1) -> Pair(1, 0)
            Pair(-1, 0) -> Pair(0, 1)
            Pair(0, -1) -> Pair(-1, 0)
            else -> throw Exception("Unknown direction '$direction'.")
        }
    }

    fun squareOnRight(): Char {
        return getSquare(location.first + onRight().first,
                location.second + onRight().second)
    }
}