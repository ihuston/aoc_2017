package net.ianhuston.adventofcode

import java.io.File

fun main(args: Array<String>) {
    val inputText = File("/Users/ihuston/code/advent/data/day6.txt").readLines()
    val initialBanks = inputText[0].split("\t").map { it.toInt() }
    println(initialBanks)
    println(countCycle(initialBanks))
}

fun redistribute(initialBanks: List<Int>): List<Int> {
    val banks = initialBanks.toMutableList()
    val start = argMax(banks) ?: return initialBanks
    var blocks = banks[start]
    banks[start] = 0
    var index = (start + 1) % banks.size
    while (blocks > 0) {
        banks[index]++
        blocks--
        index = (index + 1) % banks.size
    }
    return banks.toList()
}

fun countCycle(banks: List<Int>): Int{
    val allPreviousBanks = mutableListOf(banks)
    var counter = 1
    var nextBanks = redistribute(banks)
    while (nextBanks !in allPreviousBanks) {
        counter++
        allPreviousBanks.add(nextBanks)
        nextBanks = redistribute(nextBanks)
//        println("$counter: $nextBanks")
    }
    val previousIndex = allPreviousBanks.indexOf(nextBanks)
    return counter - previousIndex
}

fun argMax(l: List<Int>): Int? {
    return l.indices.maxBy { l[it] }
}