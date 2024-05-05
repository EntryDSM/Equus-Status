package hs.kr.equus.status.domain.status.presentation

import hs.kr.equus.status.domain.status.service.CancelApplicationSubmitService
import hs.kr.equus.status.domain.status.service.UpdateIsPrintsArrivedService
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/status")
class AdminController(
    private val cancelApplicationSubmitService: CancelApplicationSubmitService,
    private val updateIsPrintsArrivedService: UpdateIsPrintsArrivedService
) {
    @PatchMapping("/submitted/{receipt-code}")
    fun cancelApplicationSubmit(@PathVariable("receipt-code") receiptCode: Long) {
        cancelApplicationSubmitService.execute(receiptCode)
    }

    @PatchMapping("/prints-arrived/{receipt-code}")
    fun updateIsPrintsArrivedService(
        @PathVariable("receipt-code")
        receiptCode: Long,
        @RequestParam("is_prints_arrived")
        isPrintsArrived: Boolean
    ) {
        updateIsPrintsArrivedService.execute(receiptCode, isPrintsArrived)
    }
}
