package aoc.challenges

import aoc.AdventUtils
import java.util.*
import kotlin.math.abs

/**
 * @author Harrison | Hc747
 * Contact: harrisoncole05@gmail.com | harrison.cole@uts.edu.au | harrison.cole-1@student.uts.edu.au
 * @version 1.0
 * @since 2018-12-06
 */
class Node(val x: Int, val y: Int) {

    var infinite: Boolean = false

    override fun equals(other: Any?): Boolean {
        return other is Node && other.x == x && other.y == y
    }

    override fun hashCode(): Int {
        return Objects.hash(x, y)
    }

    fun distanceBetween(x: Int, y: Int): Int {
        return abs(this.x - x) + abs(this.y - y)
    }

}

data class InactiveNode(val x: Int, val y: Int, val nearest: Node?)

data class ChallengeContext(val active: List<Node>, val inactive: List<InactiveNode>)

open class Day6 : DailyChallenge<ChallengeContext, Int, Int> {

    override val input: ChallengeContext = let {

        val active = AdventUtils.readLines(6).map { line ->
            val (x, y) = line.split(",").map { coordinate -> coordinate.trim() }
            Node(x.toInt(), y.toInt())
        }

        val (x1, x2) = let {
            val sorted = active.sortedBy { node -> node.x }
            arrayOf(sorted.first().x, sorted.last().x)
        }

        val (y1, y2) = let {
            val sorted = active.sortedBy { node -> node.y }
            arrayOf(sorted.first().y, sorted.last().y)
        }

        val inactive = mutableListOf<InactiveNode>()

        for (x in x1..x2) {
            for (y in y1..y2) {

                val nearest = active.map { node -> node.distanceBetween(x, y) to node }.sortedBy { pair -> pair.first }

                val (a, b) = nearest
                val (d1, node) = a
                val (d2) = b

                inactive += InactiveNode(x, y, if (d1 == d2) null else node)

                if (x == x1 || x == x2 || y == y1 || y == y2) {
                    node.infinite = true
                }
            }
        }

        ChallengeContext(active, inactive)
    }

    override fun first(): Int {
        val (active, inactive) = input
        val filtered = active.filter { !it.infinite }

        return filtered.map { node -> inactive.count { it.nearest == node } }.sortedDescending().first()
    }

    override fun second(): Int {
        val (active, inactive) = input

        return inactive.count { node -> active.sumBy { it.distanceBetween(node.x, node.y) } < 10_000 }
    }

}