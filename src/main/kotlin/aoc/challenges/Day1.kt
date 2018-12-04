package aoc.challenges

import aoc.AdventUtils

/**
 * @author Harrison | Hc747
 * Contact: harrisoncole05@gmail.com | harrison.cole@uts.edu.au | harrison.cole-1@student.uts.edu.au
 * @version 1.0
 * @since 2018-12-02
 */
class TemporalDevice(var frequency: Int = 0) {

    fun calibrate(vararg offsets: Int) {
        frequency += offsets.fold(0) { curr, next -> curr + next }
    }

}

open class Day1 : DailyChallenge<List<Int>, Int, Int> {

    override val input: List<Int> = AdventUtils.readLines(1).map { Integer.parseInt(it) }

    override fun first(): Int {
        val device = TemporalDevice()

        device.calibrate(*input.toIntArray())

        return device.frequency
    }

    override fun second(): Int {
        val device = TemporalDevice()
        val visited = HashSet<Int>()

        while (true) {
            for (frequency in input) {
                visited += device.frequency

                device.calibrate(frequency)

                if (device.frequency in visited) {
                    return device.frequency
                }
            }
        }
    }

}