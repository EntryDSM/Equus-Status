package hs.kr.equus.status.domain.status.service

import hs.kr.equus.status.domain.status.domain.repository.StatusRepository
import hs.kr.equus.status.domain.status.exception.StatusNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UpdateIsPrintsArrivedService(
    private val statusRepository: StatusRepository
) {
    fun execute(receiptCode: Long, isPrintsArrived: Boolean) {
        val status = statusRepository.findByReceiptCode(receiptCode)
            ?: throw StatusNotFoundException

        status.changeIsPrintsArrivedStatus(isPrintsArrived)
    }
}
