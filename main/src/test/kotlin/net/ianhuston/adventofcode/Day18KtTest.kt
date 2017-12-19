package net.ianhuston.adventofcode

import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.sendBlocking
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class Day18KtTest {
    lateinit var myProgram: Day18Program

    @Before
    fun setup() {
        val registers = ('a'..'z').associate { Pair(it, 0L) }.toMutableMap()
        myProgram = Day18Program(registers = registers, instructions = emptyList(),
                pointer = 0)
    }

    @Test
    fun testSnd() {
        myProgram.registers['x'] = 128L
        val channel = Channel<Long>(10)
        val result = runBlocking {
            myProgram.snd('x', channel)
            channel.receive()
        }
        assertEquals(128L, result)
    }

    @Test
    fun testSet() {
        myProgram.registers['c'] = 100L
        myProgram.registers['d'] = 1L
        assertEquals(100L, myProgram.set('d', myProgram.getValue('c')).registers['d'])
    }

    @Test
    fun testAdd() {
        myProgram.registers['c'] = 100L
        myProgram.registers['d'] = 1L
        assertEquals(101L, myProgram.add('d', myProgram.getValue('c')).registers['d'])
    }

    @Test
    fun testMul() {
        myProgram.registers['c'] = 100L
        myProgram.registers['d'] = 1L
        assertEquals(100L, myProgram.mul('d', myProgram.getValue('c')).registers['d'])
    }

    @Test
    fun testMod() {
        myProgram.registers['c'] = 10L
        myProgram.registers['d'] = 21L
        assertEquals(1L, myProgram.mod('d', myProgram.getValue('c')).registers['d'])
    }

    @Test
    fun testRcv() {
        myProgram.registers['c'] = 1L
        val channel = Channel<Long>(10)
        runBlocking {
            channel.send(101L)
            myProgram.rcv('c', channel)
        }
        assertEquals(101L, myProgram.registers['c'])
    }

    @Test
    fun testJgz() {
        myProgram.instructions = listOf("set a 1",
                "add a 2",
                "mul a a",
                "mod a 5")
        myProgram.registers['c'] = 1L
        myProgram.registers['d'] = 2L
        myProgram.jgz(myProgram.getValue('c'), myProgram.getValue('d').toInt())
        assertEquals(1, myProgram.pointer)
    }

    @Test
    fun testGetValue() {
        myProgram.registers['c'] = 100L
        assertEquals(100L, myProgram.getValue('c'))
        assertEquals(100L, myProgram.getValue(100L))
        assertEquals(100L, myProgram.getValue("100"))
        assertEquals(100L, myProgram.getValue("c"))
    }

//    @Test
//    fun testRunPart1() {
//        val inputText = "set a 1\n" +
//                "add a 2\n" +
//                "mul a a\n" +
//                "mod a 5\n" +
//                "snd a\n" +
//                "set a 0\n" +
//                "rcv a\n" +
//                "jgz a -1\n" +
//                "set a 1\n" +
//                "jgz a -2"
//
//        assertEquals(4L, day18(inputText))
//
//    }

    @Test
    fun testParse() {
        val inputText = "set a 1\n" +
                "add a 2\n" +
                "mul a a\n" +
                "mod a 5\n" +
                "snd a\n" +
                "set a 0\n" +
                "rcv a\n" +
                "jgz a -1\n" +
                "set a 1\n" +
                "jgz a -2"
        val expected = listOf("set a 1",
                        "add a 2",
                        "mul a a",
                        "mod a 5",
                        "snd a",
                        "set a 0",
                        "rcv a",
                        "jgz a -1",
                        "set a 1",
                        "jgz a -2")

        assertEquals(expected, parseDay18Input(inputText))
    }
}