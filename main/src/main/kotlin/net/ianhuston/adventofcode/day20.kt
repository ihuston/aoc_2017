package net.ianhuston.adventofcode

import java.io.File
import kotlin.math.abs
import kotlin.math.pow

fun main(args: Array<String>) {
    val input = File("/Users/ihuston/code/aoc_2017/data/day20.txt").readText()
    println("Closest Particle: " + minimumAccel(parseDay20Input(input)))
    println("# Remaining Particles: " + numberOfParticlesRemainingAtEnd(parseDay20Input(input), 1000))
}

fun parseDay20Input(input: String): List<Particle> {
    return input
            .lines()
            .filter { it != "" }
            .map {
                Particle(
                        getPosition(it),
                        getVelocity(it),
                        getAccel(it))
            }
}

private fun getAccel(s: String): Triple<Int, Int, Int> {
    val elements =  s
            .substringAfterLast(", a=<")
            .substringBefore(">")
            .split(",")
            .map { it.trim().toInt() }
    return Triple(elements[0], elements[1], elements[2])

}

private fun getPosition(s: String): Triple<Int, Int, Int> {
    val elements =  s
            .substringAfterLast("p=<")
            .substringBefore(">, v")
            .split(",")
            .map { it.trim().toInt() }
    return Triple(elements[0], elements[1], elements[2])

}

private fun getVelocity(s: String): Triple<Int, Int, Int> {
    val elements =  s
            .substringAfterLast(", v=<")
            .substringBefore(">, a=")
            .split(",")
            .map { it.trim().toInt() }
    return Triple(elements[0], elements[1], elements[2])

}

fun minimumAccel(particles: List<Particle>): Int {

    return particles
            .map { abs(it.initialAcceleration.first)
                + abs(it.initialAcceleration.second)
                + abs(it.initialAcceleration.third) }
            .let { it.indexOf(it.min()) }

}

data class Particle(
        val initialPosition: Triple<Int, Int, Int>,
        val initialVelocity: Triple<Int, Int, Int>,
        val initialAcceleration: Triple<Int, Int, Int>,
        var position: Triple<Int, Int, Int> = initialPosition,
        var velocity: Triple<Int, Int, Int> = initialVelocity,
        var acceleration: Triple<Int, Int, Int> = initialAcceleration) {

    fun update() {
        velocity += acceleration
        position += velocity
    }

    fun collision(other: Particle): Boolean {
        return (position.minus(other.position) == Triple(0, 0, 0))

    }

}

private operator fun Triple<Int, Int, Int>.minus(b: Triple<Int, Int, Int>): Triple<Int, Int, Int> {
    return Triple(this.first - b.first,
            this.second - b.second,
            this.third - b.third)
}

private operator fun Triple<Int, Int, Int>.plus(b: Triple<Int, Int, Int>): Triple<Int, Int, Int> {
    return Triple(this.first + b.first,
            this.second + b.second,
            this.third + b.third)
}

fun particlesRemainingAfterThisTime(particles: List<Particle>): List<Particle> {
    return particles.filter { a ->
        particles.filter { it != a }
                .all {!it.collision(a)}
    }
}

fun numberOfParticlesRemainingAtEnd(particles: List<Particle>, duration: Int): Int {
    var remainingParticles = particles
    for (t in 0 until duration) {
        particles.forEach { it.update() }
        remainingParticles = particlesRemainingAfterThisTime(remainingParticles)
    }
    return remainingParticles.size
}