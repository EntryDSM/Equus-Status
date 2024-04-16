package hs.kr.equus.status.domain.status.service

import hs.kr.equus.status.domain.status.domain.repository.StatusRepository
import hs.kr.equus.status.domain.status.exception.StatusNotFoundException
import hs.kr.equus.status.domain.status.presentation.dto.response.InternalStatusResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class GetStatusByReceiptCodeService(
    private val statusRepository: StatusRepository
) {
    fun execute(receiptCode: Long): InternalStatusResponse {
        val status = statusRepository.findByReceiptCode(receiptCode) ?: throw StatusNotFoundException

        return status.run {
            InternalStatusResponse(
                id = id,
                receiptCode = receiptCode,
                isSubmitted = isSubmitted,
                examCode = examCode,
                isFirstRoundPass = isFirstRoundPass,
                isSecondRoundPass = isSecondRoundPass,
            )
        }
    }
}
