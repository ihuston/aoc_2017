package net.ianhuston.adventofcode

import org.junit.Assert.*
import org.junit.Test

class Day20KtTest {

    @Test
    fun testParseDay20Input() {
        val input = "p=<3,0,0>, v=<2,0,0>, a=<-1,0,0>\n" +
                    "p=<4,0,0>, v=<0,0,0>, a=<-2,0,0>\n"

        val expected = listOf(
                Particle(Triple(3,0,0),
                         Triple(2,0, 0),
                         Triple(-1, 0, 0)),
                Particle(Triple(4, 0, 0),
                         Triple(0, 0, 0),
                         Triple(-2, 0, 0)))

        assertEquals(expected, parseDay20Input(input))
    }

    @Test
    fun testMinimumAccel() {
        val particles = listOf(
                Particle(Triple(3,0,0),
                        Triple(2,0, 0),
                        Triple(-1, 0, 0)),
                Particle(Triple(4, 0, 0),
                        Triple(0, 0, 0),
                        Triple(-2, 0, 0)))
        assertEquals(0, minimumAccel(particles))
    }

    @Test
    fun testParticlesRemaining() {
        val particles = parseDay20Input(
                "p=<-6,0,0>, v=< 3,0,0>, a=< 0,0,0>\n" +
                        "p=<-4,0,0>, v=< 2,0,0>, a=< 0,0,0>\n" +
                        "p=<-2,0,0>, v=< 1,0,0>, a=< 0,0,0>\n" +
                        "p=< 3,0,0>, v=<-1,0,0>, a=< 0,0,0>"
        )

        assertEquals(1, numberOfParticlesRemainingAtEnd(particles, 10))
    }

    @Test
    fun testParticleRemainingAtThisTime() {
        val particles = parseDay20Input(
                "p=<-6,0,0>, v=< 3,0,0>, a=< 0,0,0>\n" +
                        "p=<-4,0,0>, v=< 2,0,0>, a=< 0,0,0>\n" +
                        "p=<-2,0,0>, v=< 1,0,0>, a=< 0,0,0>\n" +
                        "p=< 3,0,0>, v=<-1,0,0>, a=< 0,0,0>"
        )

        assertEquals(particles, particlesRemainingAfterThisTime(particles))
        particles.forEach { it.update() }
        assertEquals(particles, particlesRemainingAfterThisTime(particles))
        particles.forEach { it.update() }
        assertEquals(particles.subList(3, particles.size),
                particlesRemainingAfterThisTime(particles))
    }
}