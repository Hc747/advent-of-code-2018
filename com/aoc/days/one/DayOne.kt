package aoc.days.one

import aoc.AdventUtils
import aoc.Challenge

/**
 * @author Harrison | Hc747
 * Contact: harrisoncole05@gmail.com | harrison.cole@uts.edu.au | harrison.cole-1@student.uts.edu.au
 * @version 1.0
 * @since 2018-12-02
 */
class TemporalDevice(var frequency: Int = 0) {

    fun calibrate(offsets: IntArray) {
        frequency += offsets.fold(0) { curr, next -> curr + next }
    }

}

object DayOne : Challenge<Int, Int> {

    override fun first(): Int {
        val input = AdventUtils.readLines(1).map { Integer.parseInt(it) }

        val device = TemporalDevice()

        device.calibrate(input.toIntArray())

        return device.frequency
    }

    override fun second(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}