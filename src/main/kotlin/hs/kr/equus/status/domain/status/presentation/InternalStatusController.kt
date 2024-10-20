package hs.kr.equus.status.domain.status.presentation

import hs.kr.equus.status.domain.status.presentation.dto.response.InternalStatusResponse
import hs.kr.equus.status.domain.status.service.GetAllStatusService
import hs.kr.equus.status.domain.status.service.GetStatusByReceiptCodeService
import hs.kr.equus.status.domain.status.service.UpdateExamCodeService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/internal/status")
class InternalStatusController(
    private val getStatusByReceiptCodeService: GetStatusByReceiptCodeService,
    private val getAllStatusService: GetAllStatusService,
    private val updateExamCodeService: UpdateExamCodeService
) {
    @GetMapping("/{receipt-code}")
    fun getStatusByReceiptCode(@PathVariable("receipt-code") receiptCode: Long): InternalStatusResponse {
        return getStatusByReceiptCodeService.execute(receiptCode)
    }

    @GetMapping("/list")
    fun getStatusByReceiptCode(): List<InternalStatusResponse> {
        return getAllStatusService.execute()
    }

    @PatchMapping("/{receipt-code}/exam-code")
    fun updateExamCode(@PathVariable("receipt-code") receiptCode: Long, @RequestParam examCode: String) {
        updateExamCodeService.execute(receiptCode, examCode)
    }
}
