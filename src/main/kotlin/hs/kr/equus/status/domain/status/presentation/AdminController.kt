package hs.kr.equus.status.domain.status.presentation

import hs.kr.equus.status.domain.status.service.CancelApplicationSubmitService
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/status")
class AdminController(
    private val cancelApplicationSubmitService: CancelApplicationSubmitService
) {
    @PatchMapping("/submitted/{receipt-code}")
    fun cancelApplicationSubmit(@PathVariable("receipt-code") receiptCode: Long) {
        cancelApplicationSubmitService.execute(receiptCode)
    }
}
