package net.ianhuston.adventofcode

fun main(args: Array<String>) {
    println(spinlock(316))
}

fun spinlock(step: Int): Int {
    var newIndex = 0
    var secondSlot = 0
    for (i in 1..50_000_000) {
        newIndex = forwardOnly(newIndex, i, step)
        if (newIndex == 1) secondSlot = i
    }
    return secondSlot
}

fun forwardOnly(current: Int, count: Int, step: Int): Int {
    return ((current + step) % count) + 1
}

fun forwardAndInsert(buffer: Pair<List<Int>, Int>, step: Int): Pair<List<Int>, Int> {
    val newList = buffer.first.toMutableList()
    val index = ((buffer.second + step) % buffer.first.size) + 1
    newList.add(index, newList.max()?.plus(1) ?: 0)
    return Pair(newList, index)
}