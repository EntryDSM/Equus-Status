package hs.kr.equus.status.domain.status.service

import hs.kr.equus.status.domain.status.domain.repository.StatusRepository
import hs.kr.equus.status.domain.status.presentation.dto.response.InternalStatusResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class GetAllStatusService(
    private val statusRepository: StatusRepository
) {
    fun execute(): List<InternalStatusResponse> {
        val status = statusRepository.findAll()

        return status.map {
            InternalStatusResponse(
                id = it.id,
                receiptCode = it.receiptCode,
                isSubmitted = it.isSubmitted,
                examCode = it.examCode,
                isFirstRoundPass = it.isFirstRoundPass,
                isSecondRoundPass = it.isSecondRoundPass
            )
        }
    }
}