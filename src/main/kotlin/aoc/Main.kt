package aoc

import aoc.challenges.Challenge
import java.util.*

/**
 * @author Harrison | Hc747
 * Contact: harrisoncole05@gmail.com | harrison.cole@uts.edu.au | harrison.cole-1@student.uts.edu.au
 * @version 1.0
 * @since 2018-12-03
 */
object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        val challenges = ServiceLoader.load(Challenge::class.java)

        challenges.forEach {

            println("Day: ${it::class.simpleName}")

            println("-".repeat(15))

            println("First Challenge: ${try {it.first()} catch (e: Throwable) { e }}")

            println("-".repeat(15))

            println("Second Challenge: ${try { it.second() } catch (e: Throwable) { e }}")

            println("-".repeat(15))
        }
    }

}