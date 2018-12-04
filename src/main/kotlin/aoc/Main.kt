package aoc

import aoc.challenges.DailyChallenge
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.system.measureTimeMillis

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

        fun execute(block: () -> Any?) = async {
            var result: Any? = null
            val duration = measureTimeMillis {
                result = try {
                    block()
                } catch (e: Throwable) {
                    e
                }
            }
            result to duration
        }

        challenges.forEach {
            val first = execute(it::first)
            val second = execute(it::second)

            println("Day: ${it::class.simpleName}")

            println("-".repeat(45))

            var result = first.await()

            println("First Challenge")
            println("Result: ${result.first}")
            println("Execution Time: ${result.second} milliseconds")

            println("-".repeat(45))

            result = second.await()

            println("Second Challenge")
            println("Result: ${result.first}")
            println("Execution Time: ${result.second} milliseconds")

            println("-".repeat(45))

            println()
        }
    }

}