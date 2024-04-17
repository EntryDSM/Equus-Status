package hs.kr.equus.status.domain.status.service

import hs.kr.equus.status.domain.status.domain.Status
import hs.kr.equus.status.domain.status.domain.repository.StatusRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateStatusService(
    private val statusRepository: StatusRepository
) {
    @Transactional
    fun execute(receiptCode: Long) {
        statusRepository.findByReceiptCode(receiptCode)
            ?: statusRepository.save(
            Status(receiptCode = receiptCode)
        )
    }
}
