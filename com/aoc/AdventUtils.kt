package aoc

import java.nio.file.Paths

/**
 * @author Harrison | Hc747
 * Contact: harrisoncole05@gmail.com | harrison.cole@uts.edu.au | harrison.cole-1@student.uts.edu.au
 * @version 1.0
 * @since 2018-12-03
 */
object AdventUtils {

    private fun file(day: Int) = Paths.get("input", "day$day.txt").toFile()!!

    fun readText(day: Int) = with(file(day)) { readText() }

    fun readLines(day: Int) = with(file(day)) { readLines() }

}