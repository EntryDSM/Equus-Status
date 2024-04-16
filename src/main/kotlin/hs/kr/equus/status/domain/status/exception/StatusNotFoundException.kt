package hs.kr.equus.status.domain.status.exception

import hs.kr.equus.status.global.error.exception.EquusException
import hs.kr.equus.status.global.error.exception.ErrorCode

object StatusNotFoundException : EquusException(ErrorCode.STATUS_NOT_FOUND) {
    val EXCEPTION = StatusNotFoundException
}
