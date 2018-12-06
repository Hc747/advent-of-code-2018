package aoc

import aoc.challenges.DailyChallenge
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
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
    fun main(args: Array<String>) {
        val challenges = ServiceLoader.load(DailyChallenge::class.java)

        fun execute(block: () -> Any?) = GlobalScope.async {
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

        fun print(challenge: String, future: Deferred<Pair<Any?, Long>>) {
            runBlocking {
                val (result, duration) = future.await()

                println("$challenge Challenge")
                println("Result: $result")
                println("Execution Time: $duration milliseconds")

                println("-".repeat(45))
            }
        }

        challenges.forEach {
            val first = execute(it::first)
            val second = execute(it::second)

            println("Day: ${it::class.simpleName}")

            println("-".repeat(45))

            print("First", first)

            print("Second", second)

            println()
        }
    }

}