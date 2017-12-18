package net.ianhuston.adventofcode

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class Day18KtTest {
    lateinit var myProgram: Program

    @Before
    fun setup() {
        val registers = ('a'..'z').associate { Pair(it, 0L) }.toMutableMap()
        myProgram = Program(registers = registers, instructions = emptyList(),
                pointer = 0)
    }

    @Test
    fun testSnd() {
        myProgram.registers['x'] = 128
        assertEquals(128, myProgram.snd(myProgram.getValue('x')).registers['!'])
    }

    @Test
    fun testSet() {
        myProgram.registers['c'] = 100
        myProgram.registers['d'] = 1
        assertEquals(100, myProgram.set('d', myProgram.getValue('c')).registers['d'])
    }

    @Test
    fun testAdd() {
        myProgram.registers['c'] = 100
        myProgram.registers['d'] = 1
        assertEquals(101, myProgram.add('d', myProgram.getValue('c')).registers['d'])
    }

    @Test
    fun testMul() {
        myProgram.registers['c'] = 100
        myProgram.registers['d'] = 1
        assertEquals(100, myProgram.mul('d', myProgram.getValue('c')).registers['d'])
    }

    @Test
    fun testMod() {
        myProgram.registers['c'] = 10
        myProgram.registers['d'] = 21
        assertEquals(1, myProgram.mod('d', myProgram.getValue('c')).registers['d'])
    }

    @Test
    fun testRcv() {
        myProgram.registers['c'] = 1
        myProgram.snd(myProgram.getValue('c'))
        assertEquals(1, myProgram.rcv(myProgram.getValue('c')))
        assertNull(myProgram.rcv(myProgram.getValue('d')))
    }

    @Test
    fun testJgz() {
        myProgram.instructions = listOf("set a 1",
                "add a 2",
                "mul a a",
                "mod a 5")
        myProgram.registers['c'] = 1
        myProgram.registers['d'] = 2
        myProgram.jgz(myProgram.getValue('c'), myProgram.getValue('d').toInt())
        assertEquals(1, myProgram.pointer)
    }

    @Test
    fun testGetValue() {
        myProgram.registers['c'] = 100
        assertEquals(100, myProgram.getValue('c'))
        assertEquals(100, myProgram.getValue(100))
        assertEquals(100, myProgram.getValue("100"))
        assertEquals(100, myProgram.getValue("c"))
    }

    @Test
    fun testRun() {
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

        assertEquals(4, day18(inputText))

    }

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