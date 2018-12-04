package aoc.challenges

import aoc.AdventUtils
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.*

/**
 * @author Harrison | Hc747
 * Contact: harrisoncole05@gmail.com | harrison.cole@uts.edu.au | harrison.cole-1@student.uts.edu.au
 * @version 1.0
 * @since 2018-12-04
 */
enum class GuardState {
    AWAKE,
    ASLEEP
}

data class Guard(val id: Int) {

    val sleep = IntArray(60)

    lateinit var state: GuardState

    lateinit var timestamp: LocalDateTime

    fun transitionState(state: GuardState, timestamp: LocalDateTime, update: Boolean = true) {
        if (update && this.state == GuardState.ASLEEP && this.state != state) {
            for (minute in this.timestamp.minute until timestamp.minute) {
                sleep[minute]++
            }
        }

        this.state = state
        this.timestamp = timestamp
    }

    val sleepiestMinute get() = sleep.indexOf(sleep.max()!!)

}

data class GuardEvent(val timestamp: LocalDateTime, val guard: Guard?, val state: GuardState?)

open class Day4 : DailyChallenge<List<Guard>, Int, Int> {

    companion object {

        private val OVERALL_PATTERN = Regex(".*(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}).*(Guard #\\d+ begins shift|wakes up|falls asleep)")
        private val GUARD_ID_PATTERN = Regex("#\\d+")
        private val TIME_FORMAT = DateTimeFormatterBuilder().append(DateTimeFormatter.ISO_LOCAL_DATE).appendLiteral(" ").append(DateTimeFormatter.ISO_LOCAL_TIME).toFormatter()

        private fun String.parse(): GuardEvent {
            val groups = OVERALL_PATTERN.matchEntire(this)!!.groups

            val timestamp = LocalDateTime.parse(groups[1]!!.value, TIME_FORMAT)
            val event = groups[2]!!.value

            val payload = when (event) {
                "wakes up" -> null to GuardState.AWAKE
                "falls asleep" -> null to GuardState.ASLEEP
                else -> {
                    val id = GUARD_ID_PATTERN.find(this)!!.groups[0]!!.value.replace("#", "")

                    Guard(id.toInt()) to null
                }
            }

            val (guard, state) = payload

            return GuardEvent(timestamp, guard, state)
        }

    }

    override val input: List<Guard> = let {
        val events = AdventUtils.readLines(4).map { it.parse() }.sortedWith(Comparator { a, b -> a.timestamp.compareTo(b.timestamp) })

        val guards = mutableMapOf<Int, Guard>()

        var guard: Guard? = null

        for (event in events) {

            if (event.guard != null) {
                guard = guards.computeIfAbsent(event.guard.id) { event.guard }
                guard.transitionState(GuardState.AWAKE, event.timestamp, update = false)
            } else {
                guard!!.transitionState(event.state!!, event.timestamp)
            }

        }

        guards.values.toList()
    }

    override fun first(): Int {
        val guard = input.sortedByDescending { it.sleep.sum() }.first()
        return guard.id * guard.sleepiestMinute
    }

    override fun second(): Int {
        val guard = input.sortedByDescending { it.sleep[it.sleepiestMinute] }.first()
        return guard.id * guard.sleepiestMinute
    }

}