package hs.kr.equus.status.domain.status.service

import hs.kr.equus.status.domain.status.domain.repository.StatusRepository
import hs.kr.equus.status.domain.status.exception.StatusNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CancelApplicationSubmitService(
    private val statusRepository: StatusRepository
) {
    @Transactional
    fun execute(receiptCode: Long) {
        val status = statusRepository.findByReceiptCode(receiptCode)
            ?: throw StatusNotFoundException.EXCEPTION
        status.cancelSubmit()
    }
}