package be.rokeppen.aoc2024.day5

import java.util.Collections

class Day5 {

    private fun getInput(file: String): Pair<List<Pair<Int, Int>>, List<List<Int>>> {
        javaClass.getResourceAsStream(file).use { f ->
            val (rulePart, orderPart) = f.bufferedReader().readText().split("\r\n\r\n")
            val rules = rulePart.split("\r\n").map {
                val (x, y) = it.split("|").map(String::toInt)
                x to y
            }
            val orders = orderPart.split("\r\n").map { it.split(",").map(String::toInt) }
            return rules to orders
        }
    }

    private fun validate(order: List<Int>, rule: Pair<Int, Int>): Boolean {
        return !order.contains(rule.first) || !order.contains(rule.second) ||
                order.indexOf(rule.first) < order.indexOf(rule.second)
    }

    fun solvePartOne(file: String): Int {
        val (rules, orders) = getInput(file)
        return orders.filter { o -> rules.all { validate(o, it) } }.sumOf { it[it.size / 2] }
    }

    private fun swap(list: List<Int>, rule: Pair<Int, Int>) {
        Collections.swap(list, list.indexOf(rule.first), list.indexOf(rule.second))
    }

    fun solvePartTwo(file: String): Int {
        val (rules, orders) = getInput(file)
        return orders.filter { o -> rules.any { !validate(o, it) } }
            .onEach { list ->
                var invalidRules = rules.filter { !validate(list, it) }
                while (invalidRules.isNotEmpty()) {
                    swap(list, invalidRules.first())
                    invalidRules = rules.filter { !validate(list, it) }
                }
            }.sumOf { it[it.size / 2] }
    }
}

fun main() {
    check(Day5().solvePartOne("/day5/test.txt") == 143)
    println(Day5().solvePartOne("/day5/input.txt"))
    check(Day5().solvePartTwo("/day5/test.txt") == 123)
    println(Day5().solvePartTwo("/day5/input.txt"))
}