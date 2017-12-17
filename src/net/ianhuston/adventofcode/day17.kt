package net.ianhuston.adventofcode

fun main(args: Array<String>) {
    println(spinlock(316))
}

fun spinlock(step: Int): Int {
    var buffer = Pair(listOf(0), 0)
    for (i in 1..2017) {
        buffer = forwardAndInsert(buffer, step)
    }
    return buffer.first[(buffer.second+1) % buffer.first.size]
}

fun forwardAndInsert(buffer: Pair<List<Int>, Int>, step: Int): Pair<List<Int>, Int> {
    val newList = buffer.first.toMutableList()
    val index = ((buffer.second + step) % buffer.first.size) + 1
    newList.add(index, newList.max()?.plus(1) ?: 0)
    return Pair(newList, index)
}