package hs.kr.equus.status.global.error

data class ErrorResponse(
    val status: Int,
    val message: String?
)