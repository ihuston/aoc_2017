package net.ianhuston.adventofcode

fun main(args: Array<String>) {
    println(findPairs(634, 301))
}

fun findPairs(starterA: Long, starterB: Long): Int {
    var counter = 0
    var nextA = starterA
    var nextB = starterB
    for (i in 1..5_000_000) {
        nextA = nextValue(nextA, generator=1)
        nextB = nextValue(nextB, generator=2)
        if (isMatch(Pair(nextA, nextB))) counter++
    }
    return counter
}

fun isMatch(pair: Pair<Long, Long>): Boolean {
    return pair.first.and(0xFFFFL) == pair.second.and(0xFFFFL)
}

fun nextValue(previous: Long, generator: Int): Long {
    val factors = listOf(16807, 48271)
    val thisFactor = factors[generator - 1]
    var trialValue = previous
    do {
        trialValue = (trialValue * thisFactor) % 2147483647L
    } while (trialValue % (4*generator) != 0L)

    return trialValue
}