package kr.lanthanide.chabunnote.util

fun ignoreCaseOpt(ignoreCase: Boolean) =
    if (ignoreCase) setOf(RegexOption.IGNORE_CASE) else emptySet()

fun String?.indexesOf(query: String, ignoreCase: Boolean = true): List<Int> =
    Regex.escape(query)       // to disable any special meaning of query's characters
        .toRegex(ignoreCaseOpt(ignoreCase))
        .findAll(this?: "")
        .map { it.range.first }
        .toList()