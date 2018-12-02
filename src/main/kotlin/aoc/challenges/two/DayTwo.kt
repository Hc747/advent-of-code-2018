package aoc.challenges.two

import aoc.AdventUtils
import aoc.challenges.Challenge

/**
 * @author Harrison | Hc747
 * Contact: harrisoncole05@gmail.com | harrison.cole@uts.edu.au | harrison.cole-1@student.uts.edu.au
 * @version 1.0
 * @since 2018-12-03
 */
class Box(private val identifier: String) {

    fun hasRecurringValue(occurences: Int): Boolean {
        val groups = identifier.groupBy { it }
        return groups.keys.any { groups[it]!!.size == occurences }
    }

    fun differentiate(other: Box): Pair<Int, String> {
        var differences = 0
        val sequence = mutableListOf<Char>()

        for (idx in 0 until Math.min(identifier.length, other.identifier.length)) {
            if (identifier[idx] == other.identifier[idx]) {
                sequence += identifier[idx]
            } else {
                differences++
            }
        }

        differences += Math.abs(identifier.length - other.identifier.length)

        return differences to sequence.joinToString("")
    }

}

open class DayTwo : Challenge<Int, String> {

    override fun first(): Int {
        val input = AdventUtils.readLines(2).map { Box(it) }

        return input.count { it.hasRecurringValue(2) } * input.count { it.hasRecurringValue(3) }
    }

    override fun second(): String {
        val input = AdventUtils.readLines(2).map { Box(it) }

        for (outer in input) {
            for (inner in input) {
                if (outer == inner) {
                    continue
                }

                val difference = outer.differentiate(inner)

                if (difference.first == 1) {
                    return difference.second
                }
            }
        }

        throw IllegalStateException("Unable to solve puzzle for given input.")
    }

}