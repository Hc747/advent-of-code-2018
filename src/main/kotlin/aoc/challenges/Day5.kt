package aoc.challenges

import aoc.AdventUtils
import kotlin.math.max

/**
 * @author Harrison | Hc747
 * Contact: harrisoncole05@gmail.com | harrison.cole@uts.edu.au | harrison.cole-1@student.uts.edu.au
 * @version 1.0
 * @since 2018-12-05
 */
class Polymer(val units: String) {

    fun react(): String {
        val builder = StringBuilder(units)
        var index = 0

        while (index < builder.length) {
            val current = builder[index]

            if (index + 1 >= builder.length) break

            val next = builder[index + 1]

            if (current.equals(next, ignoreCase = true) && current != next) {
                builder.deleteCharAt(index + 1).deleteCharAt(index)
                index = max(0, --index)
            } else {
                index++
            }
        }

        return builder.toString()
    }

}

class Day5 : DailyChallenge<String, Int, Int> {

    override val input = AdventUtils.readText(5).trim()

    override fun first(): Int {
        return Polymer(input).react().length
    }

    override fun second(): Int {
        return ('a'..'z').map { char ->
            val units = input.filter { !it.equals(char, ignoreCase = true) }
            Polymer(units).react().length
        }.min()!!
    }

}