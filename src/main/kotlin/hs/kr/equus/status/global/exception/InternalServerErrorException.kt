package hs.kr.equus.status.global.exception

import hs.kr.equus.status.global.error.exception.EquusException
import hs.kr.equus.status.global.error.exception.ErrorCode

object InternalServerErrorException : EquusException(
    ErrorCode.INTERNAL_SERVER_ERROR
)
