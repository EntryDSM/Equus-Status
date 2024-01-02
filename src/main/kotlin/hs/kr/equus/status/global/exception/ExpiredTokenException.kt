package hs.kr.equus.status.global.exception

import hs.kr.equus.status.global.error.exception.EquusException
import hs.kr.equus.status.global.error.exception.ErrorCode

object ExpiredTokenException : EquusException(
    ErrorCode.EXPIRED_TOKEN
)