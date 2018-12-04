package aoc.challenges

import aoc.AdventUtils

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

open class Day2 : DailyChallenge<List<Box>, Int, String> {

    override val input: List<Box> = AdventUtils.readLines(2).map { Box(it) }

    override fun first(): Int {
        return input.count { it.hasRecurringValue(2) } * input.count { it.hasRecurringValue(3) }
    }

    override fun second(): String {
        val boxes = input.toMutableList()

        val iterator = boxes.iterator()

        while (iterator.hasNext()) {
            val outer = iterator.next()

            iterator.remove()

            for (inner in boxes) {
                val difference = outer.differentiate(inner)

                if (difference.first == 1) {
                    return difference.second
                }
            }
        }

        throw IllegalStateException("Unable to solve puzzle for given input.")
    }

}