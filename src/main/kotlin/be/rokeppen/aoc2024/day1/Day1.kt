package be.rokeppen.aoc2024.day1

import kotlin.math.abs

class Day1 {

    private fun getInput(file: String): Pair<List<Int>, List<Int>> {
        val first = mutableListOf<Int>()
        val second = mutableListOf<Int>()
        javaClass.getResourceAsStream(file).use {
            it.bufferedReader().readLines().map { line ->
                val lineSplit = line.split("   ")
                first.add(lineSplit[0].toInt())
                second.add(lineSplit[1].toInt())
            }
        }
        return first to second
    }

    fun solvePartOne(file: String): Int {
        val (first, second) = getInput(file)
        return first.sorted().zip(second.sorted()).sumOf { (x, y) -> abs(x - y) }
    }

    fun solvePartTwo(file: String): Int {
        val (first, second) = getInput(file)
        return first.sumOf { f -> f * second.count { s -> s == f } }
    }
}

fun main() {
    check(Day1().solvePartOne("/day1/test.txt") == 11)
    println(Day1().solvePartOne("/day1/input.txt"))
    check(Day1().solvePartTwo("/day1/test.txt") == 31)
    println(Day1().solvePartTwo("/day1/input.txt"))
}