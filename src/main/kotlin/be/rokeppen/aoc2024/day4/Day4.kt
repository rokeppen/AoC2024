package be.rokeppen.aoc2024.day4

class Day4 {

    private val directions = listOf(
        listOf(0 to 0, 0 to 1, 0 to 2, 0 to 3),
        listOf(0 to 0, 1 to 0, 2 to 0, 3 to 0),
        listOf(0 to 0, 1 to 1, 2 to 2, 3 to 3),
        listOf(0 to 0, 1 to -1, 2 to -2, 3 to -3)
    )

    private val alteredDirection = listOf(-1 to -1, 0 to 0, 1 to 1, 1 to -1, 0 to 0, -1 to 1)

    private fun getInput(file: String): List<List<Char>> {
        javaClass.getResourceAsStream(file).use {
            return it.bufferedReader().readLines().map(CharSequence::toList)
        }
    }

    private fun List<List<Char>>.getWordOn(x: Int, y: Int, direction: List<Pair<Int, Int>>): String {
        return direction.map { (dx, dy) -> getOrNull(x + dx)?.getOrNull(y + dy) ?: "" }.joinToString("")
    }

    fun solvePartOne(file: String): Int {
        val wordSearch = getInput(file)
        return wordSearch.flatMapIndexed { x, row ->
            row.flatMapIndexed { y, _ ->
                directions.map { wordSearch.getWordOn(x, y, it) }.filter { it in listOf("XMAS", "SAMX") }
            }
        }.count()
    }

    fun solvePartTwo(file: String): Int {
        val wordSearch = getInput(file)
        return wordSearch.flatMapIndexed { x, row ->
            row.mapIndexedNotNull { y, _ ->
                wordSearch.getWordOn(x, y, alteredDirection).takeIf { it in listOf("MASMAS", "MASSAM", "SAMMAS", "SAMSAM") }
            }
        }.count()
    }
}

fun main() {
    check(Day4().solvePartOne("/day4/test.txt") == 18)
    println(Day4().solvePartOne("/day4/input.txt"))
    check(Day4().solvePartTwo("/day4/test.txt") == 9)
    println(Day4().solvePartTwo("/day4/input.txt"))
}