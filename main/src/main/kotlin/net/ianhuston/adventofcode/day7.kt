package net.ianhuston.adventofcode

import java.io.File

fun main(args: Array<String>) {
    val inputText = File("/Users/ihuston/code/advent/data/day7.txt").readText()
    println(bottomProgram(inputText))
}

data class Tower(val base: String, val subTowerNames: List<String>, val baseWeight: Int)

fun bottomProgram(input: String): String {
    return findUnequal(parseDay7Input(input)).base
}

fun findBottom(input: Map<String, Tower>): Tower {
    val allSubTowers = input.map { it.value.subTowerNames }.flatten()
    val roots = input.filter { it.value.subTowerNames.isNotEmpty() }.filter { it.key !in allSubTowers }

    return roots.values.first()
}

fun parseDay7Input(input: String): Map<String, Tower> {
    return input.split("\n").filter { it != "" }.associate{ Pair(splitLine(it).base, splitLine(it)) }
}

fun splitLine(line: String): Tower {
    val base = line.substringBefore(" (")
    val weight = line.substringAfter(" (").substringBefore(")").toInt()
    val subTowerString = line.substringAfter(") -> ", "")
    val subTower = if (subTowerString != "") {
        subTowerString.split(", ")
    } else emptyList()

    return Tower(base, subTower, weight)
}

fun listSubWeights(base: String, towers: Map<String, Tower>): List<Int> {
    val baseTower = towers[base] ?: return emptyList()
    return baseTower.subTowerNames.map { (towers[it]?.baseWeight ?:0) + listSubWeights(it, towers).sum()}
}

fun findUnequal(input: Map<String, Tower>): Tower {
    for ((base, tower) in input) {
        val subWeights = listSubWeights(base, input)
        if (!subWeights.all { it.equals(subWeights.first()) }) {
            println("$base: ${tower.baseWeight}: ${tower.subTowerNames}: $subWeights")
        }
    }
    println("mdbtyw: ${input["mdbtyw"]?.baseWeight}: ${listSubWeights("mdbtyw", input)}")
    throw Exception("No unequal weights found!")
}