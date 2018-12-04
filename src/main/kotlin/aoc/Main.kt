package aoc

import aoc.challenges.DailyChallenge
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.util.*

/**
 * @author Harrison | Hc747
 * Contact: harrisoncole05@gmail.com | harrison.cole@uts.edu.au | harrison.cole-1@student.uts.edu.au
 * @version 1.0
 * @since 2018-12-03
 */
object Main {

    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val challenges = ServiceLoader.load(DailyChallenge::class.java)

        challenges.forEach {
            val first = async { try {it.first()} catch (e: Throwable) { e } }
            val second = async { try {it.second()} catch (e: Throwable) { e } }

            println("Day: ${it::class.simpleName}")

            println("-".repeat(30))

            println("First Challenge: ${first.await()}")

            println("-".repeat(30))

            println("Second Challenge: ${second.await()}")

            println("-".repeat(30))

            println()
        }
    }

}