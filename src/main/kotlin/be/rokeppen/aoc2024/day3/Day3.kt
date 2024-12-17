package be.rokeppen.aoc2024.day3

class Day3 {

    private fun getInput(file: String): String {
        javaClass.getResourceAsStream(file).use {
            return it.bufferedReader().readLines().joinToString("")
        }
    }

    private fun String.addProducts() = Regex("mul\\(\\d{1,3},\\d{1,3}\\)").findAll(this).sumOf {
        val (f, s) = it.value.removeSurrounding("mul(", ")").split(",")
        f.toInt() * s.toInt()
    }

    fun solvePartOne(file: String): Int {
        return getInput(file).addProducts()
    }

    fun solvePartTwo(file: String): Int {
        return getInput(file).replace(Regex("don't\\(\\).*?do\\(\\)"), "")
            .replace(Regex("don't\\(\\).*"), "").addProducts()
    }
}

fun main() {
    check(Day3().solvePartOne("/day3/test.txt") == 161)
    println(Day3().solvePartOne("/day3/input.txt"))
    check(Day3().solvePartTwo("/day3/test.txt") == 48)
    println(Day3().solvePartTwo("/day3/input.txt"))
}