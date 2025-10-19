package calculator

import camp.nextstep.edu.missionutils.Console

fun main() {
    val raw = Console.readLine() ?: ""
    val input = raw.replace("\\n", "\n")

    val delimiters: MutableList<Char> = mutableListOf(',', ':')

    val body = if (input.startsWith("//")) {
        val nl = input.indexOf('\n')
        if (nl >= 0) {
            val header = input.substring(2, nl)
            header.forEach { ch ->
                if (!delimiters.contains(ch)) delimiters.add(ch)
            }
            input.substring(nl + 1)
        } else {
            input
        }
    } else {
        input
    }

    val regex = delimiters
        .map { Regex.escape(it.toString()) }
        .joinToString("|")
        .toRegex()

    val sum = body
        .split(regex)
        .filter { it.isNotBlank() }
        .sumOf { it.toInt() }

    println("결과 : $sum")
}
