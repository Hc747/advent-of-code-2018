package aoc

import aoc.challenges.DailyChallenge
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
        val challenges = ServiceLoader.load(DailyChallenge::class.java)

        challenges.forEach {

            println("Day: ${it::class.simpleName}")

            println("-".repeat(30))

            println("First Challenge: ${try {it.first()} catch (e: Throwable) { e }}")

            println("-".repeat(30))

            println("Second Challenge: ${try { it.second() } catch (e: Throwable) { e }}")

            println("-".repeat(30))

            println()
        }
    }

}