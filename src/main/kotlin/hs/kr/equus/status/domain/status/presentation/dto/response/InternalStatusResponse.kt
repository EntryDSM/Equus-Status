package hs.kr.equus.status.domain.status.presentation.dto.response

data class InternalStatusResponse(
    val id: Long,
    var isPrintsArrived: Boolean = false,
    var isSubmitted: Boolean = false,
    var examCode: String? = null,
    var isFirstRoundPass: Boolean = false,
    var isSecondRoundPass: Boolean = false,
    val receiptCode: Long,
)
