package calculator

import camp.nextstep.edu.missionutils.Console

fun main() {
    val raw = Console.readLine() ?: ""
    val input = raw.replace("\\n", "\n")

    val delimiters: MutableList<Char> = mutableListOf(',', ':')

    val body = if (input.startsWith("//")) {
        val nl = input.indexOf('\n')
        if (nl < 0) {
            throw IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.")
        }
        val header = input.substring(2, nl)
        if (header.isEmpty()) {
            throw IllegalArgumentException("커스텀 구분자가 비어 있습니다.")
        }
        header.forEach { ch ->
            if (!delimiters.contains(ch)) delimiters.add(ch)
        }
        input.substring(nl + 1)
    } else {
        input
    }

    if (body.isBlank()) {
        throw IllegalArgumentException("빈 입력은 허용되지 않습니다.")
    }

    val regex = delimiters
        .map { Regex.escape(it.toString()) }
        .joinToString("|")
        .toRegex()

    val numbers = body
        .split(regex)
        .filter { it.isNotBlank() }
        .map { token ->
            token.trim().toIntOrNull()
                ?: throw IllegalArgumentException("숫자가 아닙니다: '$token'")
        }

    if (numbers.any { it < 0 }) {
        throw IllegalArgumentException("음수는 허용되지 않습니다.")
    }

    val total = numbers.sum()
    println("결과 : $total")
}
