package hs.kr.equus.status.domain.status.service

import hs.kr.equus.status.domain.status.domain.repository.StatusRepository
import hs.kr.equus.status.domain.status.exception.StatusNotFoundException
import org.springframework.stereotype.Service

@Service
class UpdateStatusService(
    private val statusRepository: StatusRepository
) {
    fun execute(receiptCode: Long){
        val status = statusRepository.findByReceiptCode(receiptCode) ?: throw StatusNotFoundException
        status.finalSubmit()
    }
}