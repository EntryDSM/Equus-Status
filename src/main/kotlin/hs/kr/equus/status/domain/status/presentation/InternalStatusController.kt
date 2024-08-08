package hs.kr.equus.status.domain.status.presentation

import hs.kr.equus.status.domain.status.presentation.dto.response.InternalStatusResponse
import hs.kr.equus.status.domain.status.service.GetAllStatusService
import hs.kr.equus.status.domain.status.service.GetStatusByReceiptCodeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/internal/status")
class InternalStatusController(
    private val getStatusByReceiptCodeService: GetStatusByReceiptCodeService,
    private val getAllStatusService: GetAllStatusService
) {
    @GetMapping("/{receipt-code}")
    fun getStatusByReceiptCode(@PathVariable("receipt-code") receiptCode: Long): InternalStatusResponse {
        return getStatusByReceiptCodeService.execute(receiptCode)
    }

    @GetMapping("/list")
    fun getStatusByReceiptCode(): List<InternalStatusResponse> {
        return getAllStatusService.execute()
    }
}
