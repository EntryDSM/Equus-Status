package hs.kr.equus.status.global.error

import hs.kr.equus.status.global.error.exception.EquusException
import io.github.resilience4j.circuitbreaker.CallNotPermittedException
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler(
    private val messageSource: MessageSource
) {

    @ExceptionHandler(EquusException::class)
    fun handlingEquusException(e: EquusException): ResponseEntity<ErrorResponse> {
        val code = e.errorCode
        return ResponseEntity(
            ErrorResponse(code.status, code.message),
            HttpStatus.valueOf(code.status)
        )
    }

    @ExceptionHandler(CallNotPermittedException::class)
    fun handleCallNotPermittedException(e: CallNotPermittedException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(500, "server error"),
            HttpStatus.valueOf(500)
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validatorExceptionHandler(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                400,
                e.bindingResult.allErrors[0].defaultMessage
            ),
            HttpStatus.BAD_REQUEST
        )
    }
}
