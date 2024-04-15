package hs.kr.equus.status.global.exception

import hs.kr.equus.status.global.error.exception.EquusException
import hs.kr.equus.status.global.error.exception.ErrorCode

object InvalidTokenException : EquusException(
    ErrorCode.INVALID_TOKEN
)
