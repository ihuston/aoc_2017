package net.ianhuston.advent

import java.io.File
import java.net.URL

fun main(args: Array<String>) {
    val input = File("/Users/ihuston/code/advent/data/day4a.txt")
    println(countValid(input.readText()))
}

fun Boolean.toInt() = if (this) 1 else 0

fun countValid(allPassPhrases: String): Int {
    return allPassPhrases.split("\n").filter { it != "" }
            .map { noAnagramWords(it.split(" ")).toInt() }
            .sum()
}

fun noDuplicateWords(strings: List<String>): Boolean {
    return strings.count() == strings.distinct().count()
}

fun noAnagramWords(strings: List<String>): Boolean {
    return strings.count() == strings.map { sortByCharacter(it)}.distinct().count()
}

fun sortByCharacter(s: String): String = s.asSequence().sorted().joinToString("")
