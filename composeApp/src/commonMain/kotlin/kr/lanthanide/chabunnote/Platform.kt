package kr.lanthanide.chabunnote

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform