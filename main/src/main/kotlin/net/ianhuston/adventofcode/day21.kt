package net.ianhuston.adventofcode

import java.io.File

fun main(args: Array<String>) {
    val input = File("/Users/ihuston/code/aoc_2017/data/day21.txt").readText()
    val transformations = parseDay21Input(input)
    val startingGrid = listOf(".#.", "..#", "###")
    println("Number of pixels on: " + pixelsStillOn(startingGrid, transformations, 18))
}

fun parseDay21Input(inputText: String): Map<List<String>, List<String>> {
    val preRotations = inputText.lines().filter { it != "" }.associate {
        Pair(parseGrid(it.split(" => ")[0]),
                parseGrid(it.split(" => ")[1]))
    }
    val postRotations = preRotations.toMutableMap()
    preRotations.keys.forEach { t ->
        postRotations.put(flip(t), preRotations[t] ?: emptyList())
        postRotations.put(rotate90(t), preRotations[t] ?: emptyList())
        postRotations.put(flip(rotate90(t)), preRotations[t] ?: emptyList())
        postRotations.put(rotate90(rotate90(t)), preRotations[t] ?: emptyList())
        postRotations.put(flip(rotate90(rotate90(t))), preRotations[t] ?: emptyList())
        postRotations.put(rotate90(rotate90(rotate90(t))), preRotations[t] ?: emptyList())
        postRotations.put(flip(rotate90(rotate90(rotate90(t)))), preRotations[t] ?: emptyList())
    }
    return postRotations
}

fun parseGrid(s: String): List<String> {
    return s.split("/")
}

fun pixelsStillOn(startingGrid: List<String>,
                  transformations: Map<List<String>, List<String>>,
                  iterations: Int): Int {
    var grid = startingGrid
    println("Starting grid: $startingGrid")
    println("transformations: $transformations")
    for (i in 0 until iterations) {
        grid = transformBlocks(grid, transformations)
    }
    return grid.map { it.filter { it == '#' }.count() }.sum()
}

fun flip(a: List<String>): List<String> {
    return a.map { it.reversed() }
}

fun rotate90(a: List<String>): List<String> {
    val result = mutableListOf<String>()
    for (row in a.reversed()) {
        for ((i,c) in row.withIndex()) {
            if (result.size == i) result.add("")
            result[i] = result[i] + c
        }
    }
    return result
}

fun breakUp(grid: List<String>): List<List<String>> {
    val blockWidth = if (grid.size % 2 == 0) 2 else 3
    val result= mutableListOf<List<String>>()
    val numBlocksWide = grid.size / blockWidth
    val numBlocks = numBlocksWide*numBlocksWide
    for (block in 0 until numBlocks) {
        val thisBlock = mutableListOf<String>()
        for (row in 0 until blockWidth) {
            var thisRow = ""
            val startIndex = (block % numBlocksWide) * blockWidth
            thisRow = thisRow.plus(grid[(block/numBlocksWide)*blockWidth + row].substring(startIndex, startIndex + blockWidth))
            thisBlock.add(thisRow)
        }
        result.add(thisBlock)
    }
    return result
}

fun transformBlocks(grid: List<String>,
                    transformations: Map<List<String>, List<String>>): List<String> {
    val blocks = breakUp(grid)
    val translated = blocks.map { transformations[it] }
    val blockWidth = if (grid.size % 2 == 0) 2 else 3
    val numBlocksWide = grid.size / blockWidth
    return translated.chunked(numBlocksWide, {t -> joinLists(t)}).flatten()
}

fun joinLists(l: List<List<String>?>): List<String> {
    val result = mutableListOf<String>()

    for (i in 0 until (l[0]?.size ?: 0)) {
        var thisString = ""
        for (e in l) {
            thisString += e?.elementAt(i)
        }
        result.add(thisString)
    }
    return result
}