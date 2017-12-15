package net.ianhuston.adventofcode

import java.math.BigInteger

fun main(args: Array<String>) {
    println(findPairs(634, 301))
}

fun findPairs(starterA: Long, starterB: Long): Int {
    var pair = Pair(starterA, starterB)
    var counter = 0
    for (i in 1..40_000_000) {
        pair = nextValues(pair)
        if (isMatch(pair)) counter++
    }
    return counter
}

fun isMatch(pair: Pair<Long, Long>): Boolean {
    return pair.first.and(0xFFFFL) == pair.second.and(0xFFFFL)
}

fun nextValues(pair: Pair<Long, Long>): Pair<Long, Long> {
    val first = (pair.first * 16807) % 2147483647L
    val second = (pair.second * 48271) % 2147483647L
    return Pair(first, second)
}