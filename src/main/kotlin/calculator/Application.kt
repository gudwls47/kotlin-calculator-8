package calculator

import camp.nextstep.edu.missionutils.Console

fun main() {
    val input = Console.readLine() ?: ""

    val delimiters: MutableList<Char> = mutableListOf(',', ':')

    val regex = delimiters
        .map { Regex.escape(it.toString()) }
        .joinToString("|")
        .toRegex()

    val sum = input
        .split(regex)
        .sumOf { it.toInt() }

    println("결과 : $sum")
}
