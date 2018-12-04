package aoc.challenges

import aoc.AdventUtils

/**
 * @author Harrison | Hc747
 * Contact: harrisoncole05@gmail.com | harrison.cole@uts.edu.au | harrison.cole-1@student.uts.edu.au
 * @version 1.0
 * @since 2018-12-03
 */
data class Coordinate(val x: Int, val y: Int)

class ClaimsContext {

    val references = mutableMapOf<Coordinate, Int>()

    fun addClaim(claim: Claim) {
        claim.coordinates.forEach {
            references.compute(it) { _, v -> v?.inc() ?: 1 }
        }
    }

}

class Claim(val id: Int, left: Int, top: Int, width: Int, height: Int) {

    val coordinates: List<Coordinate>

    init {
        coordinates = mutableListOf()
        for (x in left until left + width) {
            for (y in top until top + height) {
                coordinates += Coordinate(x, y)
            }
        }
    }

}

open class Day3 : DailyChallenge<Int, Int> {

    companion object {

        private fun String.parse(): Claim {
            val input = toLowerCase().split("@", ":", limit = 3).map { it.trim() }

            val id = input[0].replace("#", "").toInt()

            var data = input[1].split(",")

            val left = data[0].toInt()
            val top = data[1].toInt()

            data = input[2].split("x")

            val width = data[0].toInt()
            val height = data[1].toInt()

            return Claim(id, left, top, width, height)
        }

    }

    override fun first(): Int {
        val context = ClaimsContext()

        AdventUtils.readLines(3).forEach { context.addClaim(it.parse()) }

        return context.references.entries.count { it.value >= 2 }
    }

    override fun second(): Int {
        val context = ClaimsContext()
        val input = AdventUtils.readLines(3).map {
            val claim = it.parse()

            context.addClaim(claim)

            claim
        }

        return input.find { claim -> claim.coordinates.all { coordinate -> context.references[coordinate] == 1 } }?.id
            ?: throw IllegalStateException("Unable to solve puzzle for given input.")
    }

}