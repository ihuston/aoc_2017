package net.ianhuston.adventofcode

fun main(args: Array<String>) {
    val inputLengths = "102,255,99,252,200,24,219,57,103,2,226,254,1,0,69,216"
    val knotList = processLengths(prepareInput(inputLengths))
    println(knotList.list)
    println("Part A: " + knotList.list[0]*knotList.list[1])
    println("Part B: ${knotHash(inputLengths)}")
}

fun prepareInput(input: String): List<Int> {
    return input.map { it.toInt() }.plus(listOf(17, 31, 73, 47, 23))
}

fun knotHash(inputString: String): String {
    val input = prepareInput(inputString)

    val knotList = processLengths(input, 64)
    val hashed = denseHash(knotList.list)
    return convertToHex(hashed)

}

fun convertToHex(input: List<Int>): String {
    return input.map { it.toString(16).padStart(2, '0') }.joinToString("")
}

fun denseHash(input: List<Int>): List<Int> {
    return input.chunked(16) { it.reduce {x,y -> x xor y}}
}

fun processLengths(lengths: List<Int>, numIterations: Int=1): KnotList {
    var knotList = KnotList((0..255).toList(), 0, 0)

    for (i in 1..numIterations) {
        lengths.forEach {
            knotList = knotList.applyLength(it)
        }
    }
    return knotList
}


data class KnotList(val list: List<Int>, val currentPosition: Int, val skipSize: Int) {

    fun applyLength(length: Int): KnotList {


        val leftBeforeWrap = list.size - currentPosition
        val reversedSubList = wrappedSubList(length).reversed()

        val newList = if (leftBeforeWrap >= length) {
            // don't need to wrap
            list.subList(0, currentPosition) + reversedSubList +
                    list.subList(currentPosition + length, list.size)
        } else {
            // need to wrap
            val forEnd = reversedSubList.subList(0, leftBeforeWrap)
            val forStart = reversedSubList.subList(leftBeforeWrap, reversedSubList.size)
            forStart + list.subList(length - leftBeforeWrap, currentPosition) + forEnd
        }

        val newCurrentPosition = (currentPosition + length + skipSize) % list.size
        return KnotList(newList, newCurrentPosition, skipSize + 1)
    }

    fun wrappedSubList(length: Int): List<Int> {
        return if ((this.currentPosition + length) > this.list.size) {
            val remainder = (this.currentPosition+length) % this.list.size
            this.list.subList(this.currentPosition, this.list.size) + this.list.subList(0, remainder)
        } else {
            this.list.subList(this.currentPosition, this.currentPosition + length)
        }

    }
}