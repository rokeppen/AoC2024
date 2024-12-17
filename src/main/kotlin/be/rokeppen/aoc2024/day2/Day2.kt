package be.rokeppen.aoc2024.day2

class Day2 {

    private fun getInput(file: String): List<List<Int>> {
        javaClass.getResourceAsStream(file).use {
            return it.bufferedReader().readLines().map { line ->
                line.split(" ").map(String::toInt)
            }
        }
    }

    private fun isValid(list: List<Int>): Boolean {
        val diffs = list.windowed(2, 1).map { (f, s, _) -> f - s }
        return diffs.all { it in -3..-1 } || diffs.all { it in 1..3 }
    }

    fun solvePartOne(file: String): Int {
        return getInput(file).count(::isValid)
    }

    fun solvePartTwo(file: String): Int {
        return getInput(file).count { List(it.size) { i -> it.filterIndexed { i2, _ -> i != i2 } }.any(::isValid) }
    }
}

fun main() {
    check(Day2().solvePartOne("/day2/test.txt") == 2)
    println(Day2().solvePartOne("/day2/input.txt"))
    check(Day2().solvePartTwo("/day2/test.txt") == 4)
    println(Day2().solvePartTwo("/day2/input.txt"))
}