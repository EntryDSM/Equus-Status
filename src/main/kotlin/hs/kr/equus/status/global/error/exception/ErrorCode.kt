package hs.kr.equus.status.global.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String
) {
    // Internal Server Error
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    // UnAuthorization
    INVALID_TOKEN(401, "Invalid Token"),
    EXPIRED_TOKEN(401, "Expired Token"),

    STATUS_NOT_FOUND(404, "Status Not Found")
}
