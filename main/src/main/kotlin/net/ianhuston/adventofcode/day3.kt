package net.ianhuston.adventofcode

import kotlin.math.abs

fun main(args: Array<String>) {
    println(spiralCount(265149))
}

fun spiralCount(n: Int): Int {
//    After k blocks you are at <-k, -k>
    var k=0
    var remaining_steps = n - 1
    var x=0
    var y=0

    while (remaining_steps > 0) {

        // go right
        val right_steps = 2*k + 1
        for (i in 1..right_steps) {
            x++
            remaining_steps--
            if (remaining_steps == 0) return abs(x) + abs(y)
        }

        // go up
        val up_steps = 2*k + 1
        for (i in 1..up_steps) {
            y++
            remaining_steps--
            if (remaining_steps == 0) return abs(x) + abs(y)
        }

        // go left
        val left_steps = 2*k + 2
        for (i in 1..left_steps) {
            x--
            remaining_steps--
            if (remaining_steps == 0) return abs(x) + abs(y)
        }
        // go down
        val down_steps = 2*k + 2
        for (i in 1..down_steps) {
            y--
            remaining_steps--
            if (remaining_steps == 0) return abs(x) + abs(y)
        }
        // next block
        k++
    }
    return 0
}
